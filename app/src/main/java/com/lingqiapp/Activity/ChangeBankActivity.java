package com.lingqiapp.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.ChangeBankBean;
import com.lingqiapp.Bean.CodeBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.lingqiapp.Activity
 *
 * @author 赵磊
 * @date 2018/9/14
 * 功能描述：
 */
public class ChangeBankActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.et_banknum)
    EditText etBanknum;
    @BindView(R.id.et_bank2num)
    EditText etBank2num;
    @BindView(R.id.et_bankusername)
    EditText etBankusername;
    @BindView(R.id.et_bankname)
    EditText etBankname;
    @BindView(R.id.et_phonecode)
    EditText etPhonecode;
    @BindView(R.id.btn_getSMScode)
    TextView btnGetSMScode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Timer timer;
    private TimerTask task;
    private int time = 100;
    private String phonecode;
    private String banknum;
    private String bank2num;
    private String bankname;
    private String bankusername;

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        task = null;
        if (timer != null) {
            timer = null;
        }
        phonecode = null;
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_changebank;
    }

    @Override
    protected void initview() {
    }

    @Override
    protected void initListener() {
        btnGetSMScode.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submit();
                break;
            case R.id.btn_getSMScode:
                if (time == 100) {
                    getcaptcha(String.valueOf(SpUtil.get(context, "tel", "")));
                }
                break;
            default:
                break;
        }
    }

    private void getcaptcha(String phone) {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        btnGetSMScode.setText("" + time);
                        if (time < 0) {
                            timer.cancel();
                            btnGetSMScode.setText(getString(R.string.Get__verification_code));
                            btnGetSMScode.setEnabled(true);
                            time = 100;
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
        //// TODO: 2017/5/18  发送验证码
        if (Utils.isConnected(context)) {
            getUserPlace(phone);
        } else {
            Toast.makeText(context, getString(R.string.Networkexception), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 发送验证码
     */
    private void getUserPlace(String phone) {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("tel", phone);
        params.put("type", "3");
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/tel", "login/tel", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    Toast.makeText(ChangeBankActivity.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {


                    } else {
                        time = 0;
                    }
                    decode = null;
                    codeBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    time = 0;
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                time = 0;
            }
        });
    }

    /**
     * 注册提交
     */
    private void submit() {
        // validate

        banknum = etBanknum.getText().toString().trim();
        if (TextUtils.isEmpty(banknum)) {
            Toast.makeText(this, etBanknum.getHint().toString().trim(), Toast.LENGTH_SHORT).show();
            return;
        }
        bank2num = etBank2num.getText().toString().trim();
        if (TextUtils.isEmpty(bank2num)) {
            Toast.makeText(this, etBank2num.getHint().toString().trim(), Toast.LENGTH_SHORT).show();
            return;
        }

        bankname = etBankname.getText().toString().trim();
        if (TextUtils.isEmpty(bankname)) {
            Toast.makeText(this, etBankname.getHint().toString().trim(), Toast.LENGTH_SHORT).show();
            return;
        }
        bankusername = etBankusername.getText().toString().trim();
        if (TextUtils.isEmpty(bankusername)) {
            Toast.makeText(this, etBankusername.getHint().toString().trim(), Toast.LENGTH_SHORT).show();
            return;
        }
        phonecode = etPhonecode.getText().toString().trim();
        if (TextUtils.isEmpty(phonecode)) {
            Toast.makeText(this, getString(R.string.Please_enter_verification_code), Toast.LENGTH_SHORT).show();
            return;
        }
        getRegister();
    }

    /**
     * 注册id
     */
    private void getRegister() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("code", etPhonecode.getText().toString().trim());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("bank_code", banknum);
        params.put("bank_codes", bank2num);
        params.put("name", bankusername);
        params.put("kaihu", bankname);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "bank/change_bank", "bank/change_bank", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                time = 0;
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    ChangeBankBean changeBankBean = new Gson().fromJson(result, ChangeBankBean.class);
                    EasyToast.showShort(context, changeBankBean.getMsg());
                    if (1 == changeBankBean.getStatus()) {
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                time = 0;
                error.printStackTrace();
            }
        });
    }


}

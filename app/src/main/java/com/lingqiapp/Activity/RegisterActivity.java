package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Bean.CodeBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Utils.Validator;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_phonecode)
    EditText etPhonecode;
    @BindView(R.id.btn_getSMScode)
    TextView btnGetSMScode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_passwordagain)
    EditText etPasswordagain;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.et_tuijiangma)
    EditText etTuijiangma;
    private Timer timer;
    private TimerTask task;
    private int time = 100;
    private Context context;
    private String account;
    private String phonecode;
    private String password;
    private String passwordagain;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        context = RegisterActivity.this;
        initView();
        initData();
    }

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
        account = null;
        phonecode = null;
        password = null;
        passwordagain = null;
        System.gc();
    }

    private void initData() {
    }


    private void initView() {
        dialog = Utils.showLoadingDialog(context);
        btnGetSMScode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvXieyi.setOnClickListener(this);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_xieyi:
                startActivity(new Intent(context, ZhuCeXieYiDetailsActivity.class));
                break;
            case R.id.btn_register:
                if (cbCheck.isChecked()){
                    submit();
                }else {
                    EasyToast.showShort(context,getString(R.string.agreement_registration_first));
                }
                break;
            case R.id.btn_getSMScode:
                account = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(this, getString(R.string.Please_enter_email), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Validator.isEmail(account)) {
                    Toast.makeText(this, getString(R.string.Please_enter_email_true), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (time == 100) {
                    getcaptcha(etAccount.getText().toString());
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
        params.put("email", phone);
        params.put("type", "1");
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/mail"+ App.LanguageTYPEHTTP, "login/mail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    Toast.makeText(RegisterActivity.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
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
        account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, getString(R.string.Please_enter_email), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validator.isEmail(account)) {
            Toast.makeText(this, getString(R.string.Please_enter_email_true), Toast.LENGTH_SHORT).show();
            return;
        }

        phonecode = etPhonecode.getText().toString().trim();
        if (TextUtils.isEmpty(phonecode)) {
            Toast.makeText(this, getString(R.string.Please_enter_verification_code), Toast.LENGTH_SHORT).show();
            return;
        }

        password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,  getString(R.string.Please_enter_password), Toast.LENGTH_SHORT).show();
            return;
        }

        passwordagain = etPasswordagain.getText().toString().trim();
        if (TextUtils.isEmpty(passwordagain)) {
            Toast.makeText(this,  getString(R.string.Please_enter_password_again), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordagain)) {
            Toast.makeText(this,  getString(R.string.passwords_do_not_match), Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.show();
        getRegister(account, phonecode, password);

    }

    /**
     * 注册id
     */
    private void getRegister(String phone, String code, String password) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("email", phone);
        params.put("code", code);
        params.put("password", password);
        params.put("fpassword", password);
        params.put("tel2", etTuijiangma.getText().toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/register"+ App.LanguageTYPEHTTP, "login/register", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                time = 0;
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        Toast.makeText(RegisterActivity.this, getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    decode = null;
                    codeBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                time = 0;
                error.printStackTrace();
            }
        });
    }


}

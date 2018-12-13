package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.BankBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.CommomDialog;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.lingqiapp.Activity
 *
 * @author 赵磊
 * @date 2018/9/14
 * 功能描述：
 */
public class BankMessageActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_num)
    TextView tvBankNum;
    @BindView(R.id.tv_bank_username)
    TextView tvBankUsername;
    @BindView(R.id.ll_bank)
    LinearLayout llBank;
    @BindView(R.id.btn_addbank)
    Button btnAddbank;
    @BindView(R.id.img_delete)
    ImageView imgDelete;
    private Dialog dialog;
    private BankBean bankBean;

    @Override
    protected int setthislayout() {
        return R.layout.activity_bankmessage_layout;
    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ChangeBankActivity.class));
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommomDialog(context, R.style.dialog, getString(R.string.suretodelete), new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, final boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            AddBank();
                        }
                    }
                }).setTitle(getString(R.string.prompt)).show();
            }
        });

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            getBank();
        } else {
            EasyToast.showShort(context, getString(R.string.Networkexception));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 银行卡获取
     */
    private void getBank() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "bank/index" + App.LanguageTYPEHTTP, "bank/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    dialog.dismiss();
                    bankBean = new Gson().fromJson(result, BankBean.class);
                    if (0 == bankBean.getStatus()) {
                        llEmpty.setVisibility(View.VISIBLE);
                        llBank.setVisibility(View.GONE);
                    } else {
                        llEmpty.setVisibility(View.GONE);
                        llBank.setVisibility(View.VISIBLE);

                        tvBankName.setText(bankBean.getRes().getBank());
                        tvBankNum.setText(bankBean.getRes().getNo());
                        tvBankUsername.setText(getString(R.string.Thecardholder) + bankBean.getRes().getName());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });
    }


    /**
     * 银行卡删除
     */
    private void AddBank() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", bankBean.getRes().getId());
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "bank/delete_bank" + App.LanguageTYPEHTTP, "bank/delete_bank", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    dialog.show();
                    getBank();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });
    }


}

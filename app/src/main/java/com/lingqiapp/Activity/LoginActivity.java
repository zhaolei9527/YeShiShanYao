package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.LoginBean;
import com.lingqiapp.Bean.WXBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Utils.Validator;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by 赵磊 on 2017/7/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.rl)
    LinearLayout rl;
    @BindView(R.id.et_passwd)
    EditText etPasswd;
    @BindView(R.id.rl2)
    LinearLayout rl2;
    @BindView(R.id.tv_forgetpassworld)
    TextView tvForgetpassworld;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.rl4)
    RelativeLayout rl4;
    @BindView(R.id.btn_weixin)
    Button btnWeixin;
    @BindView(R.id.rl5)
    LinearLayout rl5;
    @BindView(R.id.rl6)
    LinearLayout rl6;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private Dialog dialog;
    private int pswminlen = 6;
    private String account;
    private String password;
    private String openid = "";

    @Override
    protected void ready() {
        super.ready();
       /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activcity_login;
    }

    @Override
    protected void initview() {
        initView();
    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(this);
        tvForgetpassworld.setOnClickListener(this);
        btnWeixin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        dialog = Utils.showLoadingDialog(context);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent();
        intent.setAction("LoginActivityIsStart");
        sendBroadcast(intent);
    }


    private void gotoMain() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    private String mesg;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(context, RegisterActivity.class));
                break;
            case R.id.btn_login:
                submit();
                break;
            case R.id.tv_forgetpassworld:
                startActivity(new Intent(context, ForgetActivity.class));
                break;
            case R.id.btn_weixin:
                dialog.show();
                Platform facebook = ShareSDK.getPlatform(Facebook.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                facebook.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        dialog.dismiss();
                        mesg = arg0.getDb().exportData();
                        Log.e("LoginActivity", mesg);
                        WXBean wxBean = new Gson().fromJson(mesg, WXBean.class);
                        openid = wxBean.getUnionid();
                        SpUtil.putAndApply(context, "wxopenid", openid);
                        getLogin("", "", openid);
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                EasyToast.showShort(context, getString(R.string.Authorization_to_cancel));
                            }
                        });
                    }
                });
                facebook.showUser(null);//授权并获取用户信息
                break;
            default:
                break;
        }
    }

    private void initView() {
    }

    private void submit() {
        // validate
        account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, getString(R.string.Please_enter_email), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validator.isEmail(account)) {
            Toast.makeText(context, getString(R.string.Please_enter_email_true), Toast.LENGTH_SHORT).show();
            return;
        }

        password = etPasswd.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.Please_enter_password), Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < pswminlen) {
            Toast.makeText(this, getString(R.string.password_length_six), Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
        dialog.show();
        getLogin(account, password, openid);

    }

    /**
     * 登录获取
     */
    private void getLogin(final String tel, final String password, final String openid) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("email", tel);
        params.put("password", password);
        if (!TextUtils.isEmpty(openid)) {
            params.put("openid", openid);
        }
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/dologin", "login/dologin", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    LoginBean loginBean = new Gson().fromJson(decode, LoginBean.class);
                    EasyToast.showShort(context, loginBean.getMsg().toString());
                    if ("1".equals(loginBean.getStatus())) {
                        SpUtil.putAndApply(context, "uid", loginBean.getUser().getId().toString());
                        SpUtil.putAndApply(context, "username", loginBean.getUser().getNi_name());
                        SpUtil.putAndApply(context, "money", loginBean.getUser().getMoney());
                        SpUtil.putAndApply(context, "img", loginBean.getUser().getImg());
                        SpUtil.putAndApply(context, "password", password);
                        if (!TextUtils.isEmpty(openid)) {
                            SpUtil.putAndApply(context, "wxopenid", openid);
                        }
                        SpUtil.putAndApply(context, "tel", "" + loginBean.getUser().getEmail());
                        SpUtil.putAndApply(context, "lv", loginBean.getUser().getIs_hui());
                        SpUtil.putAndApply(context, "zw_count", "" + loginBean.getZw_count());
                        gotoMain();
                    } else if ("2".equals(loginBean.getStatus())) {
                        btnLogin.setText(getString(R.string.Binding_to_log_in));
                        EasyToast.showShort(context, getString(R.string.binding_login));
                    } else {
                        EasyToast.showShort(context, loginBean.getMsg().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.queues.cancelAll("login/login");
        account = null;
        password = null;
        System.gc();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

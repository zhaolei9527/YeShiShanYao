package com.lingqiapp.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.CodeBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
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
public class YiJianFanKuiActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_Content)
    EditText etContent;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected int setthislayout() {
        return R.layout.activity_yijianfankui;
    }

    @Override
    protected void initview() {

    }

    private Dialog dialog;

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(etTitle.getText().toString().trim())) {
                    EasyToast.showShort(context,  getString(R.string.Please_enter_title));
                    return;
                }

                if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
                    EasyToast.showShort(context,  getString(R.string.Please_enter_content));
                    return;
                }

                if (Utils.isConnected(context)) {
                    dialog = Utils.showLoadingDialog(context);
                    dialog.show();
                    feedBack();
                } else {
                    EasyToast.showShort(context,  getString(R.string.Networkexception));
                }

            }
        });

    }

    /**
     * 注册id
     */
    private void feedBack() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("title", etTitle.getText().toString().trim());
        params.put("f_content", etContent.getText().toString().trim());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/feedback", "about/feedback", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    dialog.dismiss();
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    EasyToast.showShort(context, codeBean.getMsg());

                    if (1 == codeBean.getStatus()) {
                        finish();
                    }

                    decode = null;
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


    @Override
    protected void initData() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

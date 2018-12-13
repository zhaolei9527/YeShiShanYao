package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Intent;
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
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.TiXianBean;
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
public class TiXianActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_tixianjilu)
    TextView tvTixianjilu;
    @BindView(R.id.tv_tixianmin)
    TextView tvTixianmin;
    @BindView(R.id.tv_feilv)
    TextView tvFeilv;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Dialog dialog;
    private TiXianBean tiXianBean;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tixian_layout;
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String money = etMoney.getText().toString().trim();

                if (TextUtils.isEmpty(money)) {
                    EasyToast.showShort(context, etMoney.getHint().toString());
                    return;
                }

                double v = Double.parseDouble(money);
                String tx_min = tiXianBean.getTdata().getTx_min();
                double v1 = Double.parseDouble(tx_min);

                if (v < v1) {
                    EasyToast.showShort(context, getString(R.string.not_less_than) + v1 + "元");
                    return;
                }

                if (Utils.isConnected(context)) {
                    dialog.show();
                    doTx();
                } else {
                    EasyToast.showShort(context,  getString(R.string.Networkexception));
                }

            }
        });

        tvTixianjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, TiXianJiLuListActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            getTixian();

        } else {
            EasyToast.showShort(context,  getString(R.string.Networkexception));
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
    private void getTixian() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/tixian"+ App.LanguageTYPEHTTP, "about/tixian", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    dialog.dismiss();
                    tiXianBean = new Gson().fromJson(decode, TiXianBean.class);
                    if (1 == tiXianBean.getStatus()) {

                        tvYue.setText(getString(R.string.maximum_balance) + tiXianBean.getTdata().getMoney());
                        tvTixianmin.setText(tiXianBean.getTdata().getTx_min() + "元");
                        tvFeilv.setText(tiXianBean.getTdata().getTi_rate() + "%");
                        tvBank.setText(tiXianBean.getBank().getBank() + "  "+getString(R.string.tail)+" (" + tiXianBean.getBank().getNo().substring(tiXianBean.getBank().getNo().length() - 4)
                                + ")");
                    } else if (decode.contains("\\u60a8\\u8fd8\\u672a\\u7ed1\\u5b9a\\u94f6\\u884c\\u5361,\\u4e0d\\u80fd\\u63d0\\u73b0")) {
                        EasyToast.showShort(context, tiXianBean.getMsg());
                        startActivity(new Intent(context, BankMessageActivity.class));
                        finish();
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


    private void doTx() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("bank", tiXianBean.getBank().getBank());
        params.put("no", tiXianBean.getBank().getNo());
        params.put("money", etMoney.getText().toString());
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/do_tx"+ App.LanguageTYPEHTTP, "about/do_tx", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("LoginActivity", decode);
                try {
                    dialog.dismiss();
                    EasyToast.showShort(context, new Gson().fromJson(decode, TiXianBean.class).getMsg());
                    if ("1".equals(String.valueOf(tiXianBean.getStatus()))) {
                        finish();
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


}

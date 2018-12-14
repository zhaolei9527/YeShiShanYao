package com.lingqiapp.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.HuiYuanBean;
import com.lingqiapp.Bean.OrderWxpayBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.Constants;
import com.lingqiapp.Utils.DateUtils;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.MYSimpleDraweeView;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.lingqiapp.Activity
 *
 * @author 赵磊
 * @date 2018/9/19
 * 功能描述：
 */
public class HuiYuanShengJiActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.SimpleDraweeView)
    MYSimpleDraweeView SimpleDraweeView;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_lv1)
    TextView tvLv1;
    @BindView(R.id.tv_lv2)
    TextView tvLv2;
    @BindView(R.id.tv_lv3)
    TextView tvLv3;
    @BindView(R.id.tv_msg_content)
    TextView tvMsgContent;
    @BindView(R.id.tv_lv_time1)
    TextView tvLvTime1;
    @BindView(R.id.btn_submit1)
    Button btnSubmit1;
    @BindView(R.id.tv_lv_time2)
    TextView tvLvTime2;
    @BindView(R.id.btn_submit2)
    Button btnSubmit2;
    @BindView(R.id.tv_lv_time3)
    TextView tvLvTime3;
    @BindView(R.id.btn_submit3)
    Button btnSubmit3;
    @BindView(R.id.tv_youxiaoqi)
    TextView tvYouxiaoqi;
    @BindView(R.id.ll_youxiaoqi)
    LinearLayout llYouxiaoqi;
    private Dialog dialog;
    private IWXAPI api;

    @Override
    protected int setthislayout() {
        return R.layout.activity_huiyuanshengji_layout;
    }

    @Override
    protected void initview() {

        if (String.valueOf(SpUtil.get(context, "img", "")).contains(".com")) {
            SimpleDraweeView.setImageURI(String.valueOf(SpUtil.get(context, "img", "")));
        } else {
            SimpleDraweeView.setImageURI(UrlUtils.URL + String.valueOf(SpUtil.get(context, "img", "")));
        }
        tvUsername.setText(String.valueOf(SpUtil.get(context, "username", "")));


        if ("1".equals(String.valueOf(SpUtil.get(context, "lv", "1")))) {
            tvContent.setText("您还不是领七商城会员,开通会员，立享权益");
            llYouxiaoqi.setVisibility(View.GONE);
        } else {
            tvContent.setText("尊敬的领七商城会员,续费会员，续享权益");
            llYouxiaoqi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
            api.registerApp(Constants.APP_ID);
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            aboutHuiYuan();
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
     * 获取页面信息
     */
    private void aboutKaiTong(String hid) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("hid", hid);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/kaitong"+ App.LanguageTYPEHTTP, "about/kaitong", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    dialog.dismiss();
                    OrderWxpayBean orderWxpayBean = new Gson().fromJson(result, OrderWxpayBean.class);
                    if (api != null) {
                        PayReq req = new PayReq();
                        req.appId = Constants.APP_ID;
                        req.partnerId = orderWxpayBean.getData().getMch_id();
                        req.prepayId = orderWxpayBean.getData().getPrepay_id();
                        req.packageValue = "Sign=WXPay";
                        req.nonceStr = orderWxpayBean.getData().getNonceStr();
                        req.timeStamp = orderWxpayBean.getData().getTimeStamp();
                        req.sign = "aaaaa";
                        api.sendReq(req);
                    }
                    orderWxpayBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 获取页面信息
     */
    private void aboutHuiYuan() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/huiyuan"+ App.LanguageTYPEHTTP, "about/huiyuan", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    dialog.dismiss();
                    final HuiYuanBean huiYuanBean = new Gson().fromJson(result, HuiYuanBean.class);
                    if ("1".equals(String.valueOf(huiYuanBean.getStatus()))) {
                        tvLv1.setText(huiYuanBean.getTdate().get(0).getMoney());
                        tvLv2.setText(huiYuanBean.getTdate().get(1).getMoney());
                        tvLv3.setText(huiYuanBean.getTdate().get(2).getMoney());
                        tvLvTime1.setText(huiYuanBean.getTdate().get(0).getTerm());
                        tvLvTime2.setText(huiYuanBean.getTdate().get(1).getTerm());
                        tvLvTime3.setText(huiYuanBean.getTdate().get(2).getTerm());
                        tvMsgContent.setText(huiYuanBean.getLdate().getHui());
                        btnSubmit1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                aboutKaiTong(huiYuanBean.getTdate().get(0).getId());
                            }
                        });
                        btnSubmit2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                aboutKaiTong(huiYuanBean.getTdate().get(1).getId());
                            }
                        });
                        btnSubmit3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                aboutKaiTong(huiYuanBean.getTdate().get(2).getId());
                            }
                        });

                        if (!"1".equals(String.valueOf(SpUtil.get(context, "lv", "1")))) {
                            String Hui_star = DateUtils.getDay(Long.parseLong(huiYuanBean.getUdate().getHui_star()) * 1000);
                            String Hui_end = DateUtils.getDay(Long.parseLong(huiYuanBean.getUdate().getHui_end()) * 1000);
                            tvYouxiaoqi.setText("有效期：" + Hui_star + "至" + Hui_end);
                            Log.e("HuiYuanShengJiActivity", "tvYouxiaoqi.getText():" + tvYouxiaoqi.getText());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

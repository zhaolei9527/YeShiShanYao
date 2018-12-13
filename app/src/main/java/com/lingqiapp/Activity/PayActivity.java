package com.lingqiapp.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.BankEvent;
import com.lingqiapp.Bean.OrderVisaBean;
import com.lingqiapp.Bean.PayResult;
import com.lingqiapp.Bean.PayYueBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.Constants;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Visa.CardActivity;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lingqiapp.R.id.btn_paynow;
import static com.lingqiapp.R.id.rl_back;
import static com.lingqiapp.R.id.tv_ordernumber;


public class PayActivity extends BaseActivity implements View.OnClickListener {

    @BindView(rl_back)
    FrameLayout rlBack;
    @BindView(tv_ordernumber)
    TextView tvOrdernumber;
    @BindView(R.id.tv_totalmoney)
    TextView tvTotalmoney;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    @BindView(R.id.Choosedweixin)
    CheckBox Choosedweixin;
    @BindView(R.id.img_yue)
    ImageView imgYue;
    @BindView(R.id.Choosedyue)
    CheckBox Choosedyue;
    @BindView(btn_paynow)
    Button btnPaynow;
    @BindView(R.id.rl_weixinpay)
    RelativeLayout rlWeixinpay;
    @BindView(R.id.rl_yuepay)
    RelativeLayout rlYuepay;
    private String orderid;
    private String order;
    private Dialog dialog;
    private IWXAPI api;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    Log.e("PayActivity", resultInfo.toString());
                    String resultStatus = payResult.getResultStatus();
                    Log.e("PayActivity", resultStatus.toString());
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        EasyToast.showShort(context, "支付成功");
                        EventBus.getDefault().post(
                                new BankEvent("good", "pay"));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        EasyToast.showShort(context, "支付失败，请重试");
                        EventBus.getDefault().post(
                                new BankEvent("bad", "pay"));
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isfinish) {
            isfinish = !isfinish;
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/pay");
        //反注册EventBus
        EventBus.getDefault().unregister(PayActivity.this);
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initview() {
        orderid = getIntent().getStringExtra("orderid");
        order = getIntent().getStringExtra("order");
        if (!TextUtils.isEmpty(order)) {
            tvOrdernumber.setText(order);
        }

        //注册EventBus
        if (!EventBus.getDefault().isRegistered(PayActivity.this)) {
            EventBus.getDefault().register(PayActivity.this);
        }

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        dialog = Utils.showLoadingDialog(context);
    }

    @Override
    protected void initListener() {
        btnPaynow.setOnClickListener(this);
        rlBack.setOnClickListener(this);

        rlWeixinpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choosedweixin.setChecked(true);
                Choosedyue.setChecked(false);
            }
        });

        rlYuepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choosedweixin.setChecked(false);
                Choosedyue.setChecked(true);
            }
        });

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            tvOrdernumber.setText(getIntent().getStringExtra("orderid"));
            tvTotalmoney.setText(getIntent().getStringExtra("monsy"));
        } else {
            finish();
            EasyToast.showShort(context, getString(R.string.Networkexception));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case btn_paynow:
                if (Utils.isConnected(context)) {
                    if (Choosedweixin.isChecked()) {
                        orderWxpay();
                        return;
                    } else {
                        payYue(getIntent().getStringExtra("orderid"));
                    }
                    if (!dialog.isShowing()) {
                        dialog.show();
                        btnPaynow.setClickable(false);
                    }

                } else {
                    EasyToast.showShort(context, getString(R.string.Networkexception));
                }
                break;
            case rl_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 订单支付
     */
    private void orderWxpay() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("oid", orderid);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("orderWxpay", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/visa"+ App.LanguageTYPEHTTP, "order/visa", params, new VolleyInterface(context) {
            private Intent intent;
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("OrderActivity", result);
                try {
                    dialog.dismiss();
                    OrderVisaBean orderVisaBean = new Gson().fromJson(result, OrderVisaBean.class);
                    if (1 == orderVisaBean.getStatus()) {
                        intent = new Intent(context, CardActivity.class);
                        intent.putExtra("money", String.valueOf(orderVisaBean.getZmoney()));
                        intent.putExtra("oid", orderVisaBean.getPayorder());
                        intent.putExtra("shippingFirstName", orderVisaBean.getUdate().getName());
                        intent.putExtra("shippingLastName", "");
                        intent.putExtra("shippingEmail", String.valueOf(SpUtil.get(context, "tel", "")));
                        intent.putExtra("shippingPhone", orderVisaBean.getUdate().getTel());
                        intent.putExtra("shippingZipcode", "475000");
                        intent.putExtra("shippingSstate", orderVisaBean.getUdate().getCountry());
                        intent.putExtra("shippingCity", orderVisaBean.getUdate().getCountry());
                        intent.putExtra("shippingAddress", orderVisaBean.getUdate().getAddress());
                        intent.putExtra("shippingZipcode", "475000");
                        intent.putExtra("firstname", orderVisaBean.getUdate().getName());
                        intent.putExtra("lastname", "");
                        intent.putExtra("email", String.valueOf(SpUtil.get(context, "tel", "")));
                        intent.putExtra("phone", orderVisaBean.getUdate().getTel());
                        intent.putExtra("country", orderVisaBean.getUdate().getCountry());
                        intent.putExtra("state", orderVisaBean.getUdate().getCountry());
                        intent.putExtra("city", orderVisaBean.getUdate().getCountry());
                        intent.putExtra("address", orderVisaBean.getUdate().getAddress());
                        intent.putExtra("zipcode", "475000");
                        startActivity(intent);
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
     * 订单生成
     */
    private void payYue(final String oid) {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("oid", oid);
        Log.e("OrderActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/pay_yue"+ App.LanguageTYPEHTTP, "order/pay_yue", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("OrderActivity", result);
                try {
                    btnPaynow.setClickable(true);
                    dialog.dismiss();
                    PayYueBean payYueBean = new Gson().fromJson(result, PayYueBean.class);
                    if (1 == payYueBean.getStatus()) {
                        startActivity(new Intent(context, GoodPayActivity.class)
                                .putExtra("type", "good")
                                .putExtra("order", oid)
                                .putExtra("orderid", oid));
                        finish();
                    } else {
                        EasyToast.showShort(context, payYueBean.getMsg());
                        startActivity(new Intent(context, GoodPayActivity.class)
                                .putExtra("order", oid)
                                .putExtra("msg", payYueBean.getMsg())
                                .putExtra("orderid", oid));
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

    public static boolean isfinish = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BankEvent event) {
        if (!TextUtils.isEmpty(event.getmType())) {
            if ("pay".equals(event.getmType())) {
                if ("good".equals(event.getMsg())) {
                    startActivity(new Intent(context, GoodPayActivity.class)
                            .putExtra("type", "good")
                            .putExtra("order", order)
                            .putExtra("orderid", orderid));
                    finish();
                } else if ("bad".equals(event.getMsg())) {
                    startActivity(new Intent(context, GoodPayActivity.class)
                            .putExtra("order", order)
                            .putExtra("orderid", orderid));
                    finish();
                }
            }
        }
    }

}

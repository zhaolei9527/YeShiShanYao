package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.BankEvent;
import com.lingqiapp.Bean.GoodsOrderBean;
import com.lingqiapp.Bean.OrderOrderBean;
import com.lingqiapp.Bean.OrderWxpayBean;
import com.lingqiapp.Bean.OrderYueBean;
import com.lingqiapp.Bean.PayYueBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.Constants;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.img_dizhi)
    ImageView imgDizhi;
    @BindView(R.id.tv_check_address)
    TextView tvCheckAddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_add_dizhi)
    TextView tvAddDizhi;
    @BindView(R.id.img_checkaddress)
    ImageView imgCheckaddress;
    @BindView(R.id.rl_change_dizhi)
    RelativeLayout rlChangeDizhi;
    @BindView(R.id.ll_oreders)
    LinearLayout llOreders;
    @BindView(R.id.shopnow)
    Button shopnow;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_goods)
    TextView tvGoods;
    @BindView(R.id.img_dismiss)
    ImageView imgDismiss;
    @BindView(R.id.img_checkwechat)
    ImageView imgCheckwechat;
    @BindView(R.id.ll_checkwechat)
    LinearLayout llCheckwechat;
    @BindView(R.id.img_checkali)
    ImageView imgCheckali;
    @BindView(R.id.ll_checkali)
    LinearLayout llCheckali;
    @BindView(R.id.tv_paymoney)
    TextView tvPaymoney;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    private IWXAPI api;

    private Dialog dialog;
    private String yue;
    private double yuev;
    private int addressCode = 200;
    private String addressID = "";
    private OrderOrderBean orderOrderBean;
    private OrderYueBean orderYueBean;
    private GoodsOrderBean goodsOrderBean;
    private String type;
    private String checklv = "404";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/index");
        App.getQueues().cancelAll("order/order");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == addressCode) {
            if (data == null) {

            } else {
                tvAddDizhi.setVisibility(View.INVISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvDizhi.setVisibility(View.VISIBLE);
                tvPhone.setVisibility(View.VISIBLE);
                String name = data.getStringExtra("name");
                tvName.setText(name);
                String phone = data.getStringExtra("phone");
                tvPhone.setText(phone);
                String address = data.getStringExtra("address");
                tvDizhi.setText(address);
                addressID = data.getStringExtra("addressid");
            }
        }
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_order;
    }

    @Override
    protected void initview() {
        llPay.setVisibility(View.GONE);
        rlBack.setOnClickListener(this);
        imgDismiss.setOnClickListener(this);
        llCheckali.setOnClickListener(this);
        llCheckwechat.setOnClickListener(this);
        tvPay.setOnClickListener(this);
        shopnow.setOnClickListener(this);

        //注册EventBus
        if (!EventBus.getDefault().isRegistered(OrderActivity.this)) {
            EventBus.getDefault().register(OrderActivity.this);
        }

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlChangeDizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(context, AddressActivitry.class).putExtra("type", "backAddress"), addressCode);
            }
        });
    }

    int num = 0;

    @Override
    protected void initData() {
        String order = getIntent().getStringExtra("order");

        type = getIntent().getStringExtra("type");

        if (!TextUtils.isEmpty(type)) {

            goodsOrderBean = new Gson().fromJson(order, GoodsOrderBean.class);

            Log.e("OrderActivity", order);
            tvMoney.setText("￥" + goodsOrderBean.getZ_price());

            if (goodsOrderBean.getDizhi() != null) {
                tvAddDizhi.setVisibility(View.INVISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvName.setText(goodsOrderBean.getDizhi().getName());
                tvDizhi.setVisibility(View.VISIBLE);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(goodsOrderBean.getDizhi().getProvince());
                stringBuilder.append(goodsOrderBean.getDizhi().getCity());
                stringBuilder.append(goodsOrderBean.getDizhi().getCountry());
                stringBuilder.append(goodsOrderBean.getDizhi().getAddress());
                tvDizhi.setText(stringBuilder.toString());
                tvPhone.setVisibility(View.VISIBLE);
                tvPhone.setText(goodsOrderBean.getDizhi().getTel());
                //地址id
                addressID = goodsOrderBean.getDizhi().getId();
            } else {
                tvAddDizhi.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.INVISIBLE);
                tvDizhi.setVisibility(View.INVISIBLE);
                tvPhone.setVisibility(View.INVISIBLE);
            }

            Log.e("OrderActivity", order);

            View inflate = View.inflate(context, R.layout.item_oreder_layout, null);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.SimpleDraweeView);
            simpleDraweeView.setImageURI(UrlUtils.URL + goodsOrderBean.getGoods().getImg_feng());
            TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
            tv_title.setText(goodsOrderBean.getGoods().getTitle());
            TextView tv_price = (TextView) inflate.findViewById(R.id.tv_price);
            tv_price.setText("￥" + goodsOrderBean.getGoods().getPrice());
            TextView tv_num = (TextView) inflate.findViewById(R.id.tv_num);
            tv_num.setText(getString(R.string.number_of) + goodsOrderBean.getG_num());

            TextView tv_xiaojigoodsnum = (TextView) inflate.findViewById(R.id.tv_xiaojigoodsnum);
            tv_xiaojigoodsnum.setText(getString(R.string.total_of) + goodsOrderBean.getG_num() + getString(R.string.items));

            TextView tv_xiaojigoodprice = (TextView) inflate.findViewById(R.id.tv_xiaojigoodprice);
            tv_xiaojigoodprice.setText("￥" + goodsOrderBean.getZ_price());

            llOreders.addView(inflate);
            num = num + Integer.parseInt(goodsOrderBean.getG_num());

            tvGoods.setText(getString(R.string.total_of) + num + getString(R.string.items));

            tvPaymoney.setText("￥ " + goodsOrderBean.getZ_price());

            if (Utils.isConnected(context)) {
                dialog = Utils.showLoadingDialog(context);
            } else {
                EasyToast.showShort(context, getString(R.string.Networkexception));
            }


        } else {
            orderOrderBean = new Gson().fromJson(order, OrderOrderBean.class);

            Log.e("OrderActivity", order);
            tvMoney.setText("￥" + orderOrderBean.getZf_money());

            if (orderOrderBean.getAddress() != null) {
                tvAddDizhi.setVisibility(View.INVISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvName.setText(orderOrderBean.getAddress().getName());
                tvDizhi.setVisibility(View.VISIBLE);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(orderOrderBean.getAddress().getProvince());
                stringBuilder.append(orderOrderBean.getAddress().getCity());
                stringBuilder.append(orderOrderBean.getAddress().getCountry());
                stringBuilder.append(orderOrderBean.getAddress().getAddress());
                tvDizhi.setText(stringBuilder.toString());
                tvPhone.setVisibility(View.VISIBLE);
                tvPhone.setText(orderOrderBean.getAddress().getTel());
                //地址id
                addressID = orderOrderBean.getAddress().getId();
            } else {
                tvAddDizhi.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.INVISIBLE);
                tvDizhi.setVisibility(View.INVISIBLE);
                tvPhone.setVisibility(View.INVISIBLE);
            }

            Log.e("OrderActivity", order);
            for (int i = 0; i < orderOrderBean.getCart().size(); i++) {

                View inflate = View.inflate(context, R.layout.item_oreder_layout, null);
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.SimpleDraweeView);
                simpleDraweeView.setImageURI(UrlUtils.URL + orderOrderBean.getCart().get(i).getImg_feng());
                TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
                tv_title.setText(orderOrderBean.getCart().get(i).getTitle());
                TextView tv_price = (TextView) inflate.findViewById(R.id.tv_price);
                tv_price.setText("￥" + orderOrderBean.getCart().get(i).getPrice());
                TextView tv_num = (TextView) inflate.findViewById(R.id.tv_num);
                tv_num.setText(getString(R.string.number_of) + orderOrderBean.getCart().get(i).getNumber());

                TextView tv_xiaojigoodsnum = (TextView) inflate.findViewById(R.id.tv_xiaojigoodsnum);
                tv_xiaojigoodsnum.setText(getString(R.string.total_of) + orderOrderBean.getCart().get(i).getNumber() +getString( R.string.items));

                TextView tv_xiaojigoodprice = (TextView) inflate.findViewById(R.id.tv_xiaojigoodprice);
                tv_xiaojigoodprice.setText("￥" + orderOrderBean.getCart().get(i).getZmoney());

                llOreders.addView(inflate);
                num = num + Integer.parseInt(orderOrderBean.getCart().get(i).getNumber());

            }

            tvGoods.setText(getString(R.string.total_of) + num + getString(R.string.items));

            tvPaymoney.setText("￥ " + orderOrderBean.getZf_money());

            if (Utils.isConnected(context)) {
                dialog = Utils.showLoadingDialog(context);
            } else {
                EasyToast.showShort(context, getString(R.string.Networkexception));
            }

        }

    }

    /**
     * 订单生成
     */
    private void orderOrder() {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        if (TextUtils.isEmpty(type)) {
            params.put("totalprice", "" + orderOrderBean.getZf_money());
            params.put("cid", "" + getIntent().getStringExtra("cart_id"));
            params.put("gid", "" + getIntent().getStringExtra("gid"));
        } else {
            params.put("totalprice", "" + goodsOrderBean.getZ_price());
            params.put("gid", "" + goodsOrderBean.getGoods().getId());
            params.put("cid", "" + getIntent().getStringExtra("cart_id"));
        }
        params.put("aid", addressID);
        params.put("number", "" + num);
        Log.e("OrderActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/tj_order", "order/tj_order", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("OrderActivity", result);
                try {
                    orderYueBean = new Gson().fromJson(result, OrderYueBean.class);
                    if (1 == orderYueBean.getStatus()) {
                        llPay.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(context, getString(R.string.Abnormal_orders), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private int pay = 2;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopnow:
                if (TextUtils.isEmpty(addressID)) {
                    dialog.dismiss();
                    EasyToast.showShort(context, getString(R.string.receiving_address));
                    return;
                }
                dialog.show();
                orderOrder();
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.img_dismiss:
                llPay.setVisibility(View.GONE);
                break;
            case R.id.ll_checkali:
                pay = 1;
                imgCheckali.setVisibility(View.VISIBLE);
                imgCheckwechat.setVisibility(View.GONE);
                break;
            case R.id.ll_checkwechat:
                pay = 2;
                imgCheckali.setVisibility(View.GONE);
                imgCheckwechat.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_pay:
                if (pay == 0) {
                    EasyToast.showShort(context, getString(R.string.select_payment));
                    return;
                }
                if (pay == 2) {
                    dialog.show();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < orderYueBean.getOrderid().size(); i++) {
                        if (stringBuffer.length() == 0) {
                            stringBuffer.append(orderYueBean.getOrderid().get(i));
                        } else {
                            stringBuffer.append("," + orderYueBean.getOrderid().get(i));
                        }
                    }
                    payWechat(stringBuffer.toString());
                } else if (pay == 1) {
                    dialog.show();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < orderYueBean.getOrderid().size(); i++) {
                        if (stringBuffer.length() == 0) {
                            stringBuffer.append(orderYueBean.getOrderid().get(i));
                        } else {
                            stringBuffer.append("," + orderYueBean.getOrderid().get(i));
                        }
                    }
                    payYue(stringBuffer.toString());
                }
                break;
            default:
                break;
        }
    }

    /***
     *余额支付
     */
    private void payYue(final String oid) {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("oid", oid);
        if ("404".equals(checklv)) {

        } else {
            params.put("type", "200");
        }
        Log.e("OrderActivity--yue", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/pay_yue", "order/pay_yue", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("OrderActivity", result);
                try {
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


    /**
     * 微信支付
     */
    private void payWechat(final String oid) {
        HashMap<String, String> params = new HashMap<>(5);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("oid", oid);
        if ("404".equals(checklv)) {

        } else {
            params.put("type", "200");
        }
        Log.e("OrderActivity--wx", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/wxpay", "order/wxpay", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("OrderActivity", result);
                try {
                    dialog.dismiss();

                    if (result.contains("msg")) {
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
                        return;
                    }

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
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BankEvent event) {
        if (!TextUtils.isEmpty(event.getmType())) {
            if ("pay".equals(event.getmType())) {
                if ("good".equals(event.getMsg())) {
                    startActivity(new Intent(context, GoodPayActivity.class)
                            .putExtra("type", "good")
                            .putExtra("orderid", orderYueBean.getOrderid().get(0)));
                    finish();
                } else if ("bad".equals(event.getMsg())) {
                    startActivity(new Intent(context, GoodPayActivity.class)
                            .putExtra("orderid", orderYueBean.getOrderid().get(0)));
                    finish();
                }
            }
        }
    }


}

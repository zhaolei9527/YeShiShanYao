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

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.OrderDetailBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.MYSimpleDraweeView;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lingqiapp.R.id.img_checkaddress;
import static com.lingqiapp.R.id.ll_orders;
import static com.lingqiapp.R.id.rl_back;
import static com.lingqiapp.R.id.tv_bianhao;
import static com.lingqiapp.R.id.tv_brand_price;
import static com.lingqiapp.R.id.tv_order_exp;
import static com.lingqiapp.R.id.tv_order_expnum;
import static com.lingqiapp.R.id.tv_order_time;
import static com.lingqiapp.R.id.tv_price_total;


public class MyOrderDetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(rl_back)
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
    @BindView(R.id.rl_change_dizhi)
    RelativeLayout rlChangeDizhi;
    @BindView(R.id.rl_jifen)
    RelativeLayout rlJifen;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.rl_yue)
    RelativeLayout rlYue;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    @BindView(R.id.tv_weixinpay)
    TextView tvWeixinpay;
    @BindView(R.id.rl_weixinpay)
    RelativeLayout rlWeixinpay;
    @BindView(R.id.img_alipay)
    ImageView imgAlipay;
    @BindView(R.id.tv_alipay)
    TextView tvAlipay;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.btn_delete_order_info)
    Button btnDeleteOrderInfo;
    @BindView(R.id.btn_wuliu)
    Button btnWuliu;
    @BindView(R.id.btn_pingjia)
    Button btnPingJia;
    @BindView(R.id.rl_gotopay)
    LinearLayout rlGotopay;
    @BindView(R.id.tv_stu)
    TextView tvStu;
    @BindView(R.id.img_checkaddress)
    ImageView imgCheckaddress;
    @BindView(R.id.ll_orders)
    LinearLayout llOrders;
    @BindView(R.id.tv_brand_price)
    TextView tvBrandPrice;
    @BindView(R.id.tv_price_total)
    TextView tvPriceTotal;
    @BindView(R.id.tv_bianhao)
    TextView tvBianhao;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_exp)
    TextView tvOrderExp;
    @BindView(R.id.tv_order_expnum)
    TextView tvOrderExpnum;
    @BindView(R.id.tv_no)
    TextView tvNo;
    private String orderid;
    private String order;
    private Dialog dialog;
    private OrderDetailBean orderDetailBean;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/detail");
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_my_order_details;
    }

    @Override
    protected void initview() {
        initView();
        orderid = getIntent().getStringExtra("orderid");
        order = getIntent().getStringExtra("order");
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(this);
        btnWuliu.setOnClickListener(this);
        btnPingJia.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        isf = true;
    }

    private void initView() {
        rlBack = (FrameLayout) findViewById(rl_back);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        imgDizhi = (ImageView) findViewById(R.id.img_dizhi);
        tvCheckAddress = (TextView) findViewById(R.id.tv_check_address);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvDizhi = (TextView) findViewById(R.id.tv_dizhi);
        tvAddDizhi = (TextView) findViewById(R.id.tv_add_dizhi);
        rlChangeDizhi = (RelativeLayout) findViewById(R.id.rl_change_dizhi);
        llOrders = (LinearLayout) findViewById(ll_orders);
        tvBrandPrice = (TextView) findViewById(tv_brand_price);
        tvPriceTotal = (TextView) findViewById(tv_price_total);
        tvYue = (TextView) findViewById(R.id.tv_yue);
        imgWeixin = (ImageView) findViewById(R.id.img_weixin);
        tvWeixinpay = (TextView) findViewById(R.id.tv_weixinpay);
        rlWeixinpay = (RelativeLayout) findViewById(R.id.rl_weixinpay);
        imgAlipay = (ImageView) findViewById(R.id.img_alipay);
        tvAlipay = (TextView) findViewById(R.id.tv_alipay);
        rlAlipay = (RelativeLayout) findViewById(R.id.rl_alipay);
        tvBianhao = (TextView) findViewById(tv_bianhao);
        tvOrderTime = (TextView) findViewById(tv_order_time);
        imgCheckaddress = (ImageView) findViewById(img_checkaddress);
        imgCheckaddress.setVisibility(View.GONE);
        rlJifen = (RelativeLayout) findViewById(R.id.rl_jifen);
        rlYue = (RelativeLayout) findViewById(R.id.rl_yue);
        tvOrderExp = (TextView) findViewById(tv_order_exp);
        tvOrderExpnum = (TextView) findViewById(tv_order_expnum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_wuliu:
                startActivity(new Intent(context, WuLiuDetailsActivity.class).putExtra("name", orderDetailBean.getOrder().getExp()).putExtra("id", orderDetailBean.getOrder().getExpnum()));
                break;
            case R.id.btn_pingjia:
                startActivityForResult(new Intent(context, PingJiaPriceActivity.class)
                        .putExtra("orderResult", orderResult), 505);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (505 == requestCode) {
            if (200 == resultCode) {
                finish();
            }
        }
    }

    //setTimeout(function(){	angular.element('pre:last').scope().editAreaCtn = '亲爱的';	angular.element('pre:last').scope().sendTextMessage();},(new Date(2018,8,30,09,0,0)-new Date()));
    //   setInterval(function(){
    //   angular.element('pre:last').scope().editAreaCtn = '亲爱的';
    //   angular.element('pre:last').scope().sendTextMessage();
    //   },3000);
    //

    private String orderResult = "";

    /**
     * 订单详情获取
     */
    private void orderDetail() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", orderid);
        Log.e("MyOrderDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/detail" + App.LanguageTYPEHTTP, "order/detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                orderResult = result;
                Log.e("MyOrderDetailsActivity", result);
                try {
                    orderDetailBean = new Gson().fromJson(result, OrderDetailBean.class);
                    tvName.setText(orderDetailBean.getOrder().getName());
                    tvPhone.setText(orderDetailBean.getOrder().getTel());
                    tvDizhi.setText(orderDetailBean.getOrder().getAddress());


                    if (!TextUtils.isEmpty(orderDetailBean.getOrder().getNo())){
                        tvNo.setVisibility(View.VISIBLE);
                        tvNo.setText(orderDetailBean.getOrder().getNo());
                    }else {
                        tvNo.setVisibility(View.GONE);
                    }

                    String stu = orderDetailBean.getOrder().getStatus();
                    if ("1".equals(stu)) {
                        //holder.tv_order_type.setText("待付款");
                        rlGotopay.setVisibility(View.GONE);
                        tvStu.setText(getString(R.string.For_the_payment));
                    } else if ("2".equals(stu)) {
                        //holder.tv_order_type.setText("待发货");
                        rlGotopay.setVisibility(View.GONE);
                        tvStu.setText(getString(R.string.To_send_the_goods));
                    } else if ("3".equals(stu)) {
                        //holder.btn_delete_order.setVisibility(View.GONE);
                        rlGotopay.setVisibility(View.VISIBLE);
                        tvStu.setText(getString(R.string.For_the_goods));
                    } else if ("4".equals(stu)) {
                        //holder.tv_order_type.setText("待评价");
                        rlGotopay.setVisibility(View.VISIBLE);
                        tvStu.setText(getString(R.string.To_evaluate));
                    } else if ("5".equals(stu)) {
                        //holder.tv_order_type.setText("已评价");
                        rlGotopay.setVisibility(View.GONE);
                        tvStu.setText(getString(R.string.been_evaluated));
                    } else {
                        rlGotopay.setVisibility(View.GONE);
                        tvStu.setText("");
                    }

                    if (!TextUtils.isEmpty(orderDetailBean.getOrder().getExp()) && !TextUtils.isEmpty(orderDetailBean.getOrder().getExpnum())) {
                        tvOrderExp.setText(getString(R.string.Courier_company) + orderDetailBean.getOrder().getExp());
                        tvOrderExpnum.setText(getString(R.string.Courier_number) + orderDetailBean.getOrder().getExpnum());
                    }
                    if (TextUtils.isEmpty(orderDetailBean.getOrder().getTotalprice())) {
                        tvBrandPrice.setText("€0.00");
                    } else {
                        tvBrandPrice.setText("€" + orderDetailBean.getOrder().getTotalprice());
                    }
                    if (TextUtils.isEmpty(orderDetailBean.getOrder().getTotalprice())) {
                        tvPriceTotal.setText("€0.00");
                    } else {
                        tvPriceTotal.setText("€" + orderDetailBean.getOrder().getTotalprice());
                    }
                    tvBianhao.setText(getString(R.string.The_order_no) + orderDetailBean.getOrder().getOrderid());
                    tvOrderTime.setText(getString(R.string.Place_the_order_of_time) + DateUtils.getMillon(Long.parseLong(orderDetailBean.getOrder().getAddtime()) * 1000));
                    final View item_oreder_details_layout = View.inflate(context, R.layout.item_oreder_details_layout, null);
                    item_oreder_details_layout.setTag(orderDetailBean.getOrder().getGid());
                    MYSimpleDraweeView SimpleDraweeView = (MYSimpleDraweeView) item_oreder_details_layout.findViewById(R.id.SimpleDraweeView);

                    if (orderDetailBean.getOrder().getImg_feng().contains(".com")) {
                        SimpleDraweeView.setImageURI(orderDetailBean.getOrder().getImg_feng());
                    } else {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + orderDetailBean.getOrder().getImg_feng());
                    }

                    final TextView tv_title = (TextView) item_oreder_details_layout.findViewById(R.id.tv_title);
                    tv_title.setText(orderDetailBean.getOrder().getTitle());
                    TextView tv_classify = (TextView) item_oreder_details_layout.findViewById(R.id.tv_classify);
                    tv_classify.setText("€" + orderDetailBean.getOrder().getPrice());
                    TextView tv_size = (TextView) item_oreder_details_layout.findViewById(R.id.tv_size);
                    tv_size.setText("×" + orderDetailBean.getOrder().getNumber());
                    item_oreder_details_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PriceDetailsActivity.class);
                            int tag = Integer.parseInt(item_oreder_details_layout.getTag().toString());
                            intent.putExtra("id", String.valueOf(tag));
                            startActivity(intent);
                        }
                    });
                    llOrders.addView(item_oreder_details_layout);
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

    public static boolean isf = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isf) {
            boolean connected = Utils.isConnected(context);
            if (connected) {
                dialog = Utils.showLoadingDialog(context);
                dialog.show();
                orderDetail();
            } else {
                EasyToast.showShort(context, getString(R.string.Networkexception));
                finish();
            }
            isf = !isf;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

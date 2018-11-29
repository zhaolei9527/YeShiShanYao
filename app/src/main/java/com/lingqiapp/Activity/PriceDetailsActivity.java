package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.hintview.IconHintView;
import com.lingqiapp.Adapter.GoodsLoopAdapter;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.GoodsCangBean;
import com.lingqiapp.Bean.GoodsDetailBean;
import com.lingqiapp.Bean.GoodsOrderBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;
import com.lingqiapp.Utils.DensityUtils;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.PixelUtils;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Utils.WindowUtil;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceDetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.RollPagerView)
    com.jude.rollviewpager.RollPagerView RollPagerView;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.btn_jian)
    Button btnJian;
    @BindView(R.id.btn_jia)
    Button btnJia;
    @BindView(R.id.ll_checkmax)
    LinearLayout llCheckmax;
    @BindView(R.id.tv_pingjia_max)
    TextView tvPingjiaMax;
    @BindView(R.id.ll_allpingjia)
    LinearLayout llAllpingjia;
    @BindView(R.id.sdv_pingjia)
    SimpleDraweeView sdvPingjia;
    @BindView(R.id.tv_pingjia_name)
    TextView tvPingjiaName;
    @BindView(R.id.ll_star)
    LinearLayout llStar;
    @BindView(R.id.tv_pingjia_time)
    TextView tvPingjiaTime;
    @BindView(R.id.tv_pingjia_content)
    TextView tvPingjiaContent;
    @BindView(R.id.ll_has_pingjia)
    LinearLayout llHasPingjia;
    @BindView(R.id.sdv_pingjia2)
    SimpleDraweeView sdvPingjia2;
    @BindView(R.id.tv_pingjia_name2)
    TextView tvPingjiaName2;
    @BindView(R.id.ll_star2)
    LinearLayout llStar2;
    @BindView(R.id.tv_pingjia_time2)
    TextView tvPingjiaTime2;
    @BindView(R.id.tv_pingjia_content2)
    TextView tvPingjiaContent2;
    @BindView(R.id.ll_has_pingjia2)
    LinearLayout llHasPingjia2;
    @BindView(R.id.tv_no_pingjia)
    TextView tvNoPingjia;
    @BindView(R.id.wb)
    WebView wb;
    @BindView(R.id.img_shouye)
    ImageView imgShouye;
    @BindView(R.id.ll_shouye)
    LinearLayout llShouye;
    @BindView(R.id.img_shoucang)
    ImageView imgShoucang;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.ll_kefu)
    LinearLayout llKefu;
    @BindView(R.id.tv_addshop)
    TextView tvAddshop;
    @BindView(R.id.shopnow)
    TextView shopnow;
    @BindView(R.id.btn_shuliang)
    TextView btnShuliang;
    @BindView(R.id.SimpleDraweeView11)
    SimpleDraweeView SimpleDraweeView11;
    @BindView(R.id.SimpleDraweeView12)
    SimpleDraweeView SimpleDraweeView12;
    @BindView(R.id.SimpleDraweeView13)
    SimpleDraweeView SimpleDraweeView13;
    @BindView(R.id.ll_imgs1)
    LinearLayout llImgs1;
    @BindView(R.id.SimpleDraweeView21)
    SimpleDraweeView SimpleDraweeView21;
    @BindView(R.id.SimpleDraweeView22)
    SimpleDraweeView SimpleDraweeView22;
    @BindView(R.id.SimpleDraweeView23)
    SimpleDraweeView SimpleDraweeView23;
    @BindView(R.id.ll_imgs2)
    LinearLayout llImgs2;
    private Dialog dialog;
    private GoodsDetailBean goodsDetailBean;
    private String uid;
    private int i;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_price_details;
    }

    @Override
    protected void initview() {
        llAllpingjia.setOnClickListener(this);
        uid = (String) SpUtil.get(context, "uid", "");
    }

    @Override
    protected void initListener() {
        RollPagerView.setHintView(new IconHintView(context, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(context, getResources().getDimension(R.dimen.x7))));
        RollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        RollPagerView.setPlayDelay(30000);
        ViewGroup.LayoutParams layoutParams = RollPagerView.getLayoutParams();
        layoutParams.height = (int) (WindowUtil.getScreenWidth(context) * 0.8);
        RollPagerView.setLayoutParams(layoutParams);
        rlBack.setOnClickListener(this);
        llShoucang.setOnClickListener(this);
        tvAddshop.setOnClickListener(this);
        shopnow.setOnClickListener(this);
        llKefu.setOnClickListener(this);
        btnJia.setOnClickListener(this);
        btnJian.setOnClickListener(this);
        llShouye.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        // 开启 localStorage
        wb.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        wb.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        wb.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        wb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //使用自定义的WebViewClient
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                // forumContext.loadUrl("javascript:(" + readJS() + ")()");
                int w = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                //重新测量
                webView.measure(w, h);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                Toast.makeText(context, getString(R.string.hasError), Toast.LENGTH_SHORT).show();

            }
        });
        dialog = Utils.showLoadingDialog(context);
        dialog.show();
        goodsDetail();
        wb.loadUrl(UrlUtils.BASE_URL + "danye/goodsdetail?gid=" + getIntent().getStringExtra("id"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_lv:
                Intent intent3 = new Intent(context, HuiYuanShengJiActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_shouye:
                Intent intent2 = new Intent(context, MainActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
            case R.id.btn_jia:
                i = Integer.parseInt(btnShuliang.getText().toString());
                i = i + 1;
                btnShuliang.setText(String.valueOf(i));
                break;
            case R.id.btn_jian:
                i = Integer.parseInt(btnShuliang.getText().toString());
                i = i - 1;
                if (i < 1) {
                    i = 1;
                }
                btnShuliang.setText(String.valueOf(i));
                break;
            case R.id.ll_kefu:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, getString(R.string.Please_login_first));
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                if (Utils.isConnected(context)) {
                    startActivity(new Intent(context, KeFuActivity.class));
                } else {
                    EasyToast.showShort(context, getString(R.string.Networkexception));
                }
                break;
            case R.id.shopnow:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, getString(R.string.Please_login_first));
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                if (Utils.isConnected(context)) {
                    String kucun = goodsDetailBean.getGoods().getKucun();
                    int kucuni = Integer.parseInt(kucun);
                    if (kucuni > 1) {
                        orderBuy();
                        dialog.show();
                    } else {
                        EasyToast.showShort(context, getString(R.string.Insufficient_inventory));
                    }
                } else {
                    EasyToast.showShort(context, getString(R.string.Networkexception));
                }
                break;
            case R.id.ll_allpingjia:
                Intent intent = new Intent(context, PingJiaListActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_shoucang:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, getString(R.string.Please_login_first));
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                if ("0".equals(String.valueOf(goodsDetailBean.getIs_cang()))) {
                    goodsCang();
                    imgShoucang.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                    EasyToast.showShort(context, getString(R.string.Collection_success));
                    goodsDetailBean.setIs_cang("1");
                } else {
                    goodsOnCang();
                    imgShoucang.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                    EasyToast.showShort(context, getString(R.string.Cancel_collection));
                    goodsDetailBean.setIs_cang("0");
                }
                break;
            case R.id.tv_addshop:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, getString(R.string.Please_login_first));
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                String kucun = goodsDetailBean.getGoods().getKucun();
                int kucuni = Integer.parseInt(kucun);
                if (kucuni > 1) {
                    goodsDetailBean.getGoods().setKucun(String.valueOf(kucuni - 1));
                    dialog.show();
                    cartJoinCart();
                } else {
                    EasyToast.showShort(context, getString(R.string.Insufficient_inventory));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uid = (String) SpUtil.get(context, "uid", "");
        if (TextUtils.isEmpty(uid)) {
            return;
        }
    }

    /**
     * 确认订单（立即购买）
     */
    private void orderBuy() {
        final HashMap<String, String> params = new HashMap<>(1);
        params.put("g_num", btnShuliang.getText().toString());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/goods_order", "goods/goods_order", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {
                    dialog.dismiss();
                    GoodsOrderBean goodsOrderBean = new Gson().fromJson(result, GoodsOrderBean.class);
                    if ("1".equals(goodsOrderBean.getStatus())) {
                        context.startActivity(new Intent(context, OrderActivity.class)
                                .putExtra("order", result)
                                .putExtra("type", "123")
                                .putExtra("cart_id", goodsOrderBean.getCid())
                                .putExtra("gid", String.valueOf(getIntent().getStringExtra("id")))
                        );
                    } else {
                        EasyToast.showShort(context, goodsOrderBean.getMsg());
                    }
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
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
     * 产品详情获取
     */
    private void goodsDetail() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/detail", "goods/detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    goodsDetailBean = new Gson().fromJson(result, GoodsDetailBean.class);
                    if (1 == goodsDetailBean.getStatus()) {
                        SpUtil.putAndApply(context, "goodsDetail" + String.valueOf(getIntent().getStringExtra("id")), result);
                        tvTitle.setText(goodsDetailBean.getGoods().getTitle());
                        tvPrice.setText("€" + goodsDetailBean.getGoods().getPrice());
                        tvXiaoliang.setText(getString(R.string.sales) + goodsDetailBean.getGoods().getXiaoliang());
                        RollPagerView.setAdapter(new GoodsLoopAdapter(RollPagerView, goodsDetailBean.getGoods()));
                        if ("0".equals(String.valueOf(goodsDetailBean.getPj().getCount()))) {
                            tvPingjiaMax.setText(getString(R.string.Product_evaluation) + "（" + goodsDetailBean.getPj().getCount() + ")");
                            tvNoPingjia.setVisibility(View.GONE);
                            llHasPingjia.setVisibility(View.GONE);
                            llHasPingjia2.setVisibility(View.GONE);
                        } else {
                            tvNoPingjia.setVisibility(View.GONE);
                            if (goodsDetailBean.getPj().get_$0() != null) {
                                llHasPingjia.setVisibility(View.VISIBLE);
                                tvPingjiaMax.setText(getString(R.string.Product_evaluation) + "(" + goodsDetailBean.getPj().getCount() + ")");

                                if (goodsDetailBean.getPj().get_$0().getImg().contains(".com")) {
                                    sdvPingjia.setImageURI(goodsDetailBean.getPj().get_$0().getImg());
                                } else {
                                    sdvPingjia.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$0().getImg());
                                }

                                tvPingjiaName.setText(goodsDetailBean.getPj().get_$0().getNi_name());
                                tvPingjiaContent.setText(goodsDetailBean.getPj().get_$0().getPcontent());
                                String addtime = goodsDetailBean.getPj().get_$0().getAddtime();
                                tvPingjiaTime.setText(String.valueOf(DateUtils.getDay(Long.parseLong(addtime) * 1000)));
                                String xing = goodsDetailBean.getPj().get_$0().getStar();
                                Double i = Double.parseDouble(xing);
                                for (int i1 = 0; i1 < 5; i1++) {
                                    ImageView imageView = new ImageView(context);
                                    imageView.setPadding(PixelUtils.dip2px(context, 3), 0, 0, 0);
                                    if (i1 < i) {
                                        imageView.setBackgroundResource(R.mipmap.pingjia1);
                                    } else {
                                        imageView.setBackgroundResource(R.mipmap.pingjia2);
                                    }
                                    llStar.addView(imageView);
                                }
                                if (!goodsDetailBean.getPj().get_$0().getP_img().isEmpty()) {
                                    llImgs1.setVisibility(View.VISIBLE);
                                    for (int i2 = 0; i2 < goodsDetailBean.getPj().get_$0().getP_img().size(); i2++) {
                                        if (i2 == 0) {
                                            SimpleDraweeView11.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$0().getP_img().get(0));
                                        } else if (i2 == 1) {
                                            SimpleDraweeView12.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$0().getP_img().get(1));
                                        } else {
                                            SimpleDraweeView13.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$0().getP_img().get(2));
                                        }
                                    }
                                }
                            }
                            if (goodsDetailBean.getPj().get_$1() != null) {
                                llHasPingjia2.setVisibility(View.VISIBLE);
                                sdvPingjia2.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$1().getImg());
                                tvPingjiaName2.setText(goodsDetailBean.getPj().get_$1().getNi_name());
                                tvPingjiaContent2.setText(goodsDetailBean.getPj().get_$1().getPcontent());
                                String addtime2 = goodsDetailBean.getPj().get_$1().getAddtime();
                                tvPingjiaTime2.setText(String.valueOf(DateUtils.getDay(Long.parseLong(addtime2) * 1000)));
                                String xing2 = goodsDetailBean.getPj().get_$1().getStar();
                                Double i2 = Double.parseDouble(xing2);
                                for (int i1 = 0; i1 < 5; i1++) {
                                    ImageView imageView = new ImageView(context);
                                    imageView.setPadding(PixelUtils.dip2px(context, 3), 0, 0, 0);
                                    if (i1 < i2) {
                                        imageView.setBackgroundResource(R.mipmap.pingjia1);
                                    } else {
                                        imageView.setBackgroundResource(R.mipmap.pingjia2);
                                    }
                                    llStar2.addView(imageView);
                                }

                                if (!goodsDetailBean.getPj().get_$1().getP_img().isEmpty()) {
                                    llImgs2.setVisibility(View.VISIBLE);
                                    for (int i3 = 0; i3 < goodsDetailBean.getPj().get_$1().getP_img().size(); i3++) {
                                        if (i3 == 0) {
                                            SimpleDraweeView21.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$1().getP_img().get(0));
                                        } else if (i3 == 1) {
                                            SimpleDraweeView22.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$1().getP_img().get(1));
                                        } else {
                                            SimpleDraweeView23.setImageURI(UrlUtils.URL + goodsDetailBean.getPj().get_$1().getP_img().get(2));
                                        }
                                    }
                                }
                            }
                        }
                        if ("0".equals(String.valueOf(goodsDetailBean.getIs_cang()))) {
                            imgShoucang.setBackground(getResources().getDrawable(R.mipmap.pingjia2));
                        } else {
                            imgShoucang.setBackground(getResources().getDrawable(R.mipmap.pingjia1));
                        }
                    } else {
                        Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
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
     * 收藏产品
     */
    private void goodsCang() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/cang", "goods/cang", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivitycang", result);
                try {
                    GoodsCangBean goodsCangBean = new Gson().fromJson(result, GoodsCangBean.class);
                    if ("1".equals(String.valueOf(goodsCangBean.getStatus()))) {
                    } else {
                        goodsDetailBean.setIs_cang("0");
                        imgShoucang.setBackgroundResource(R.mipmap.pingjia1);
                        EasyToast.showShort(context, getString(R.string.Collection_failure));
                    }
                    goodsCangBean = null;
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
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
     * 取消收藏
     */
    private void goodsOnCang() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/nocang", "goods/nocang", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivitynocang", result);
                try {
                    GoodsCangBean goodsCangBean = new Gson().fromJson(result, GoodsCangBean.class);
                    if ("1".equals(String.valueOf(goodsCangBean.getStatus()))) {
                    } else {
                        goodsDetailBean.setIs_cang("1");
                        imgShoucang.setBackgroundResource(R.mipmap.pingjia2);
                        EasyToast.showShort(context, getString(R.string.Cancel_failure));
                    }
                    goodsCangBean = null;
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
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
     * 加入购物车
     */
    private void cartJoinCart() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("goods_num", btnShuliang.getText().toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/good_cart", "goods/good_cart", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("RegisterActivity", result);
                try {
                    GoodsCangBean goodsCangBean = new Gson().fromJson(result, GoodsCangBean.class);
                    if ("1".equals(String.valueOf(goodsCangBean.getStatus()))) {
                        EasyToast.showShort(context, getString(R.string.Added_cart_successfully));
                    } else if ("327".equals(String.valueOf(goodsCangBean.getStatus()))) {
                        EasyToast.showShort(context, getString(R.string.Join_failure_stock_shortage));
                    } else {
                        EasyToast.showShort(context, getString(R.string.Failed_add_shopping_cart));
                        String kucun = goodsDetailBean.getGoods().getKucun();
                        int kucuni = Integer.parseInt(kucun);
                        goodsDetailBean.getGoods().setKucun(String.valueOf(kucuni + 1));
                    }
                    goodsCangBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}


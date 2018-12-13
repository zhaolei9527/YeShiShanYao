package com.lingqiapp.Activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Adapter.ShouCangListAdapter;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.ShouCangBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.View.ProgressView;
import com.lingqiapp.View.SakuraLinearLayoutManager;
import com.lingqiapp.View.WenguoyiRecycleView;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

import static com.lingqiapp.R.id.rv_purchaserecord;

/**
 * com.lingqiapp.Activity
 *
 * @author 赵磊
 * @date 2018/9/27
 * 功能描述：
 */
public class MyShouCangAcitivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(rv_purchaserecord)
    WenguoyiRecycleView rvPurchaserecord;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    @BindView(R.id.btnIsChoosed)
    CheckBox btnIsChoosed;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    private int p = 1;
    private ShouCangListAdapter shopCarListAdapter;
    private Dialog dialog;
    private SakuraLinearLayoutManager line;
    private BroadcastReceiver receiver;

    @Override
    protected int setthislayout() {
        return R.layout.activity_shoucang;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rvPurchaserecord.setLayoutManager(line);
        rvPurchaserecord.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rvPurchaserecord.setFootLoadingView(progressView);
        TextView textView = new TextView(context);
        textView.setText(getString(R.string.notmore));
        rvPurchaserecord.setFootEndView(textView);
        rvPurchaserecord.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                suckleCart();
            }
        });

        btnIsChoosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnIsChoosed.isChecked()) {
                    for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                        shopCarListAdapter.getDatas().get(i).setCheck(false);
                    }
                    shopCarListAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                        shopCarListAdapter.getDatas().get(i).setCheck(true);
                    }
                    shopCarListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        context.registerReceiver(receiver, new IntentFilter("shoucangChoosedAll"));

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean choosed = intent.getBooleanExtra("Choosed", false);
                if (choosed) {
                    btnIsChoosed.setChecked(true);
                } else {
                    btnIsChoosed.setChecked(false);
                }
            }
        };


        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isConnected(context)) {
                    if (shopCarListAdapter != null) {
                        dialog.show();
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                            if (shopCarListAdapter.getDatas().get(i).isCheck()) {
                                if (stringBuilder.length() == 0) {
                                    stringBuilder.append(shopCarListAdapter.getDatas().get(i).getId());
                                } else {
                                    stringBuilder.append("," + shopCarListAdapter.getDatas().get(i).getId());
                                }
                            }
                        }
                        scDels(stringBuilder.toString());
                    }
                } else {
                    EasyToast.showShort(context, getString(R.string.Networkexception));
                }
            }
        });

    }

    @Override
    protected void initData() {

        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            suckleCart();
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
     * 购物车获取
     */
    private void suckleCart() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("page", String.valueOf(p));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("ShopCarActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/shoucang"+ App.LanguageTYPEHTTP, "about/shoucang", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("ShopCarActivity", result);
                try {
                    dialog.dismiss();
                    ShouCangBean suckleCartBean = new Gson().fromJson(result, ShouCangBean.class);
                    if ("1".equals(String.valueOf(suckleCartBean.getStatus()))) {
                        SpUtil.putAndApply(context, "ShopCarActivity", result);
                        LLEmpty.setVisibility(View.GONE);
                        if (rvPurchaserecord != null) {
                            rvPurchaserecord.setEnabled(true);
                            rvPurchaserecord.loadMoreComplete();
                        }
                        if (p == 1) {
                            shopCarListAdapter = new ShouCangListAdapter(suckleCartBean.getList(), context);
                            rvPurchaserecord.setAdapter(shopCarListAdapter);
                            rvPurchaserecord.setCanloadMore(false);
                            rvPurchaserecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(context, PriceDetailsActivity.class);
                                    intent.putExtra("id", shopCarListAdapter.getDatas().get(position).getGid());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            // shopCarListAdapter.setDatas((ArrayList<SuckleCartBean.ResBean>) suckleCartBean.getRes());
                        }
                    } else {
                        LLEmpty.setVisibility(View.VISIBLE);
                        SpUtil.putAndApply(context, "ShopCarActivity", "");
                    }
                    suckleCartBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                LLEmpty.setVisibility(View.VISIBLE);
                error.printStackTrace();
            }
        });
    }

    /**
     * 购物车获取
     */
    private void scDels(String ids) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("ids", ids);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("ShopCarActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/sc_dels"+ App.LanguageTYPEHTTP, "about/sc_dels", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("ShopCarActivity", result);
                try {
                    p = 1;
                    suckleCart();
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                LLEmpty.setVisibility(View.VISIBLE);
                error.printStackTrace();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

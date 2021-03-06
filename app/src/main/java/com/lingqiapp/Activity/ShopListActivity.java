package com.lingqiapp.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Adapter.ShopListAdapter;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.GoodsSouBean;
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

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.lingqiapp.Activity
 *
 * @author 赵磊
 * @date 2018/9/15
 * 功能描述：
 */
public class ShopListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    @BindView(R.id.ce_shi_lv)
    WenguoyiRecycleView ceShiLv;
    ShopListAdapter adapter;
    @BindView(R.id.fl_top)
    FrameLayout flTop;
    @BindView(R.id.tv_jgsx)
    TextView tvJgsx;
    @BindView(R.id.tv_jgjx)
    TextView tvJgjx;
    @BindView(R.id.tv_xlsx)
    TextView tvXlsx;
    @BindView(R.id.tv_xljx)
    TextView tvXljx;
    private int p = 1;
    private String ptype = "";
    private SakuraLinearLayoutManager line;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_shoplist_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        ceShiLv.setLayoutManager(line);
        ceShiLv.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        ceShiLv.setFootLoadingView(progressView);
        ceShiLv.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getNewsList();
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
        flTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ceShiLv.scrollToPosition(0);
            }
        });

        tvJgjx.setOnClickListener(this);
        tvJgsx.setOnClickListener(this);
        tvXljx.setOnClickListener(this);
        tvXlsx.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        dialog = Utils.showLoadingDialog(context);
        String keyworld = getIntent().getStringExtra("keyworld");

        if (!TextUtils.isEmpty(keyworld)) {
            etSearch.setText(keyworld);
            dialog.show();
            getNewsList();
        }

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    //点击搜索要做的操作
                    String trim = etSearch.getText().toString().trim();
                    if (TextUtils.isEmpty(trim)) {
                        EasyToast.showShort(context, getString(R.string.Please_enter_keywords));
                        return false;
                    }
                    dialog.show();
                    getNewsList();
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 新闻列表获取
     */
    private void getNewsList() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("page", String.valueOf(p));
        params.put("ptype", ptype);
        params.put("title", etSearch.getText().toString().trim());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("NewsListFragment", "params:" + params);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/goods_list" + App.LanguageTYPEHTTP, "goods/goods_list", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                try {
                    dialog.dismiss();
                    Log.e("NewsListFragment", decode.toString());
                    GoodsSouBean newsSearchBean = new Gson().fromJson(decode, GoodsSouBean.class);
                    if ("1".equals(String.valueOf(newsSearchBean.getStatus()))) {
                        LLEmpty.setVisibility(View.GONE);
                        if (ceShiLv != null) {
                            ceShiLv.setEnabled(true);
                            ceShiLv.loadMoreComplete();
                            ceShiLv.setCanloadMore(true);
                        }
                        if (p == 1) {
                            adapter = new ShopListAdapter(ShopListActivity.this, newsSearchBean);
                            ceShiLv.setAdapter(adapter);
                            if (newsSearchBean.getRes().size() < 10) {
                                ceShiLv.setCanloadMore(false);
                                ceShiLv.loadMoreEnd();
                            } else {
                                ceShiLv.setCanloadMore(true);
                            }
                        } else {
                            adapter.setDatas((ArrayList) newsSearchBean.getRes());
                        }
                    } else {
                        if (p != 1) {
                            p = p - 1;
                            Toast.makeText(context, getString(R.string.notmore), Toast.LENGTH_SHORT).show();
                        } else {
                            LLEmpty.setVisibility(View.VISIBLE);
                        }
                        ceShiLv.setCanloadMore(false);
                        ceShiLv.loadMoreEnd();
                    }
                    newsSearchBean = null;
                    decode = null;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jgsx:
                tvJgsx.setTextColor(getResources().getColor(R.color.bgtitle));
                tvJgjx.setTextColor(getResources().getColor(R.color.text333));
                tvXlsx.setTextColor(getResources().getColor(R.color.text333));
                tvXljx.setTextColor(getResources().getColor(R.color.text333));
                ptype = "1";
                getNewsList();
                break;
            case R.id.tv_jgjx:
                tvJgsx.setTextColor(getResources().getColor(R.color.text333));
                tvJgjx.setTextColor(getResources().getColor(R.color.bgtitle));
                tvXlsx.setTextColor(getResources().getColor(R.color.text333));
                tvXljx.setTextColor(getResources().getColor(R.color.text333));
                ptype = "2";
                getNewsList();
                break;
            case R.id.tv_xlsx:
                tvJgsx.setTextColor(getResources().getColor(R.color.text333));
                tvJgjx.setTextColor(getResources().getColor(R.color.text333));
                tvXlsx.setTextColor(getResources().getColor(R.color.bgtitle));
                tvXljx.setTextColor(getResources().getColor(R.color.text333));
                ptype = "3";
                getNewsList();
                break;
            case R.id.tv_xljx:
                tvJgsx.setTextColor(getResources().getColor(R.color.text333));
                tvJgjx.setTextColor(getResources().getColor(R.color.text333));
                tvXlsx.setTextColor(getResources().getColor(R.color.text333));
                tvXljx.setTextColor(getResources().getColor(R.color.bgtitle));
                ptype = "4";
                getNewsList();
                break;
            default:
                break;
        }
    }
}

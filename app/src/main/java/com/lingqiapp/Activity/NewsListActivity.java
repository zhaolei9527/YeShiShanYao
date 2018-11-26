package com.lingqiapp.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.lingqiapp.Adapter.NewsListAdapter;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.NewsListBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
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
 * Created by 赵磊 on 2017/9/19.
 */

public class NewsListActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.ce_shi_lv)
    WenguoyiRecycleView ceShiLv;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private int p = 1;
    private SakuraLinearLayoutManager line;
    private NewsListAdapter adapter;

    /**
     * 新闻列表获取
     */
    private void getNewsList() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("page", String.valueOf(p));
        params.put("title", etSearch.getText().toString().trim());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("NewsListFragment", "params:" + params);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "news/index", "news/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                try {
                    Log.e("NewsListFragment", decode.toString());
                    NewsListBean newsSearchBean = new Gson().fromJson(decode, NewsListBean.class);
                    if ("1".equals(String.valueOf(newsSearchBean.getStatus()))) {
                        LLEmpty.setVisibility(View.GONE);
                        if (ceShiLv != null) {
                            ceShiLv.setEnabled(true);
                            ceShiLv.loadMoreComplete();
                            ceShiLv.setCanloadMore(true);
                        }
                        if (refresh != null) {
                            refresh.setRefreshing(false);
                        }
                        if (p == 1) {
                            adapter = new NewsListAdapter(newsSearchBean.getRes(), context);
                            ceShiLv.setAdapter(adapter);
                            if (newsSearchBean.getRes().size() < 10) {
                                refresh.setRefreshing(false);
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
                        refresh.setRefreshing(false);
                        ceShiLv.setCanloadMore(false);
                        ceShiLv.loadMoreEnd();
                    }
                    newsSearchBean = null;
                    decode = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    refresh.setRefreshing(false);
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    public void getData() {

        refresh.setRefreshing(false);

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
                    getNewsList();
                    refresh.setRefreshing(false);
                }
                return false;
            }
        });
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_news_list_layout;
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
                getData();
            }
        });
        refresh.setProgressViewEndTarget(false, (int) getResources().getDimension(R.dimen.x105));
        refresh.setColorSchemeResources(R.color.colorAccent);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ceShiLv.setEnabled(false);
                        p = 1;
                        getData();
                    }
                }, 0);
            }
        });
        TextView textView = new TextView(context);
        textView.setText(getString(R.string.notmore));
        ceShiLv.setFootEndView(textView);
        refresh.setRefreshing(true);

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getData();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

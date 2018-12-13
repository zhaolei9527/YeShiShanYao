package com.lingqiapp.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Activity.NewsListActivity;
import com.lingqiapp.Adapter.NewsPageAdapter;
import com.lingqiapp.App;
import com.lingqiapp.Bean.NewsListBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.View.PagerSlidingTabStrip;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class NewsFragment extends BaseLazyFragment {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.VpNews_context)
    ViewPager VpNewsContext;
    Unbinder unbinder;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    private Context context;
    private int p = 1;
    private int lastp = 1;
    private String ncid = "";
    private List titles = new ArrayList();
    private List titleid = new ArrayList();
    private NewsPageAdapter adapter;

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, NewsListActivity.class));
            }
        });
        p = 1;
        getIndex();
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.news_fragment_layout, container, false);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 首页信息获取
     */
    private void getIndex() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("page", String.valueOf(p));
        params.put("cid", ncid);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("NewsFragment", params.toString());
        VolleyRequest.RequestPost(getActivity(), UrlUtils.BASE_URL + "news/index"+ App.LanguageTYPEHTTP, "news/index", params, new VolleyInterface(getActivity()) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("NewsFragment", decode);
                try {
                    NewsListBean newsListBean = new Gson().fromJson(decode, NewsListBean.class);
                    //新闻分类处理
                    List<NewsListBean.CateBean> cate = newsListBean.getCate();
                    titles.clear();
                    titleid.clear();
                    for (int i = -1; i < cate.size(); i++) {
                        if (i == -1) {
                            titles.add(getString(R.string.all));
                            titleid.add("");
                        } else {
                            titles.add(cate.get(i).getCate_name()+"");
                            titleid.add(cate.get(i).getId());
                        }
                    }
                    if (adapter == null) {
                        adapter = new NewsPageAdapter(getChildFragmentManager(), getActivity(), titles, titleid);
                        VpNewsContext.setAdapter(adapter);
                        tabs.setViewPager(VpNewsContext);
                    } else {
                        if (p != 1) {
                            VpNewsContext.setAdapter(adapter);
                        }
                    }
                    //缓存首页数据
                    SpUtil.putAndApply(getActivity(), "index", decode);
                    cate = null;
                    decode = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        App.getQueues().cancelAll("new/index");
    }
}

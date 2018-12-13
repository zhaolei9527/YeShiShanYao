package com.lingqiapp.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Activity.MainActivity;
import com.lingqiapp.Activity.ShopListActivity;
import com.lingqiapp.Adapter.HomeListAdapter;
import com.lingqiapp.App;
import com.lingqiapp.Bean.HomeBean;
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

import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class HomeFragment extends BaseLazyFragment {
    private Context context;
    private WenguoyiRecycleView rv_homelist;
    private SakuraLinearLayoutManager line;
    private HomeListAdapter adapter;
    private int page = 1;
    private Dialog dialog;

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            getData();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_homelist = (WenguoyiRecycleView) view.findViewById(R.id.rv_homelist);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_homelist.setLayoutManager(line);
        rv_homelist.setItemAnimator(new DefaultItemAnimator());
        view.findViewById(R.id.ll_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ShopListActivity.class));
            }
        });
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_homelist.setFootLoadingView(progressView);
        rv_homelist.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                page = page + 1;
                getData();
            }
        });
        TextView textView = new TextView(context);
        textView.setText(mContext.getString(R.string.notmore));
        rv_homelist.setFootEndView(textView);
    }

    //数据获取
    public void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        params.put("page", String.valueOf(page));
        Log.e("HomeFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "index/index"+ App.LanguageTYPEHTTP, "index/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("HomeFragment", result);
                try {
                    dialog.dismiss();
                    HomeBean homeBean = new Gson().fromJson(result, HomeBean.class);
                    if (page == 1) {
                        adapter = new HomeListAdapter((MainActivity) getActivity(), homeBean);
                        rv_homelist.setAdapter(adapter);
                    } else {
                        if (result.contains("\\u6ca1\\u6709\\u66f4\\u591a\\u7684\\u70ed\\u9500\\u5546\\u54c1")) {
                            rv_homelist.loadMoreComplete();
                            rv_homelist.loadMoreEnd();
                            rv_homelist.setCanloadMore(false);
                            return;
                        }
                        adapter.setDatas(homeBean.getList());
                        rv_homelist.loadMoreComplete();
                        rv_homelist.setCanloadMore(true);
                    }
                    homeBean = null;
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
                    if (rv_homelist != null) {
                        rv_homelist.loadMoreComplete();
                        rv_homelist.loadMoreEnd();
                        rv_homelist.setCanloadMore(false);
                    }
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
    public void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("index/index");
    }

}

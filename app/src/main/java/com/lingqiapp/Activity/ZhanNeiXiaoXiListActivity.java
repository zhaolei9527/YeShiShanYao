package com.lingqiapp.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Adapter.ZhanNeiListAdapter;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.ZhanNeiBean;
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
 * @date 2018/9/14
 * 功能描述：
 */
public class ZhanNeiXiaoXiListActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rv_txjl_list)
    WenguoyiRecycleView rvTxjlList;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private ZhanNeiListAdapter adapter;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_zhanneixiaoxi_list;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rvTxjlList.setLayoutManager(line);
        rvTxjlList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rvTxjlList.setFootLoadingView(progressView);
        rvTxjlList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                initData();
            }
        });
        TextView textView = new TextView(context);
        textView.setText( getString(R.string.notmore));
        rvTxjlList.setFootEndView(textView);
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

            dialog = Utils.showLoadingDialog(context);

            dialog.show();
            getNewsList();

        } else {
            EasyToast.showShort(context,  getString(R.string.Networkexception));
        }


    }

    private int p = 1;
    private SakuraLinearLayoutManager line;

    private void getNewsList() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("page", String.valueOf(p));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("NewsListFragment", "params:" + params);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/zhannei", "about/zhannei", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                try {
                    dialog.dismiss();
                    Log.e("NewsListFragment", decode.toString());
                    ZhanNeiBean newsSearchBean = new Gson().fromJson(decode, ZhanNeiBean.class);
                    if ("1".equals(String.valueOf(newsSearchBean.getStatus()))) {
                        LLEmpty.setVisibility(View.GONE);
                        if (rvTxjlList != null) {
                            rvTxjlList.setEnabled(true);
                            rvTxjlList.loadMoreComplete();
                            rvTxjlList.setCanloadMore(true);
                        }
                        if (p == 1) {
                            adapter = new ZhanNeiListAdapter(newsSearchBean.getList(), context);
                            rvTxjlList.setAdapter(adapter);

                            if (newsSearchBean.getList().size() < 10) {
                                rvTxjlList.setCanloadMore(false);
                                rvTxjlList.loadMoreEnd();
                            } else {
                                rvTxjlList.setCanloadMore(true);
                            }
                        } else {
                            adapter.setDatas((ArrayList) newsSearchBean.getList());
                        }
                    } else {
                        if (p != 1) {
                            p = p - 1;
                            Toast.makeText(context,  getString(R.string.notmore), Toast.LENGTH_SHORT).show();
                        } else {
                            LLEmpty.setVisibility(View.VISIBLE);
                        }
                        rvTxjlList.setCanloadMore(false);
                        rvTxjlList.loadMoreEnd();
                    }
                    newsSearchBean = null;
                    decode = null;
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
}

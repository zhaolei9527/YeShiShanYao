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
import com.lingqiapp.Adapter.TuiJianRenListAdapter;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.AboutTuiBean;
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
 * @date 2018/9/26
 * 功能描述：
 */
public class MyTuiJianRenActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rv_tjr_list)
    WenguoyiRecycleView rv_tjr_list;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private TuiJianRenListAdapter adapter;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tuijianren_list;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_tjr_list.setLayoutManager(line);
        rv_tjr_list.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_tjr_list.setFootLoadingView(progressView);
        rv_tjr_list.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                initData();
            }
        });
        TextView textView = new TextView(context);
        textView.setText(getString(R.string.notmore));
        rv_tjr_list.setFootEndView(textView);
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
            if (dialog == null) {
                dialog = Utils.showLoadingDialog(context);
            }
            dialog.show();
            getNewsList();
        } else {
            EasyToast.showShort(context, getString(R.string.Networkexception));
        }

    }

    private int p = 1;
    private SakuraLinearLayoutManager line;

    private void getNewsList() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("page", String.valueOf(p));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("NewsListFragment", "params:" + params);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/tui", "about/tui", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                try {
                    dialog.dismiss();
                    Log.e("NewsListFragment", decode.toString());
                    AboutTuiBean aboutTuiBean = new Gson().fromJson(decode, AboutTuiBean.class);
                    if ("1".equals(String.valueOf(aboutTuiBean.getStatus()))) {
                        LLEmpty.setVisibility(View.GONE);
                        if (rv_tjr_list != null) {
                            rv_tjr_list.setEnabled(true);
                            rv_tjr_list.loadMoreComplete();
                            rv_tjr_list.setCanloadMore(true);
                        }
                        if (p == 1) {
                            adapter = new TuiJianRenListAdapter(aboutTuiBean.getUlist(), context);
                            rv_tjr_list.setAdapter(adapter);

                            if (aboutTuiBean.getUlist().size() < 10) {
                                rv_tjr_list.setCanloadMore(false);
                                rv_tjr_list.loadMoreEnd();
                            } else {
                                rv_tjr_list.setCanloadMore(true);
                            }
                        } else {
                            adapter.setDatas((ArrayList) aboutTuiBean.getUlist());
                        }
                    } else {
                        if (p != 1) {
                            p = p - 1;
                            Toast.makeText(context, getString(R.string.notmore), Toast.LENGTH_SHORT).show();
                        } else {
                            LLEmpty.setVisibility(View.VISIBLE);
                        }
                        rv_tjr_list.setCanloadMore(false);
                        rv_tjr_list.loadMoreEnd();
                    }
                    aboutTuiBean = null;
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

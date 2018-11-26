package com.lingqiapp.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lingqiapp.Adapter.PingJiaListAdapter;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.GoodsPingBean;
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
public class PingJiaListActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    @BindView(R.id.ce_shi_lv)
    WenguoyiRecycleView ceShiLv;
    PingJiaListAdapter adapter;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.fl_top)
    FrameLayout flTop;
    private int p = 1;
    private SakuraLinearLayoutManager line;
    private Dialog dialog;
    String star = "";


    @Override
    protected int setthislayout() {
        return R.layout.activity_pingjialist_layout;
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
                getNewsList(star);
            }
        });
    }

    @Override
    protected void initListener() {
        flTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ceShiLv.scrollToPosition(0);
            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = "";
                btn1.setBackground(getResources().getDrawable(R.mipmap.pingjia01));
                btn2.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn3.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn4.setBackground(getResources().getDrawable(R.mipmap.pingjia02));

                btn1.setTextColor(getResources().getColor(R.color.bgfff));
                btn2.setTextColor(getResources().getColor(R.color.text333));
                btn3.setTextColor(getResources().getColor(R.color.text333));
                btn4.setTextColor(getResources().getColor(R.color.text333));

                dialog.show();
                getNewsList(star);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = "1";
                btn1.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn2.setBackground(getResources().getDrawable(R.mipmap.pingjia01));
                btn3.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn4.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn1.setTextColor(getResources().getColor(R.color.text333));
                btn2.setTextColor(getResources().getColor(R.color.bgfff));
                btn3.setTextColor(getResources().getColor(R.color.text333));
                btn4.setTextColor(getResources().getColor(R.color.text333));
                dialog.show();
                getNewsList(star);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = "2";
                btn1.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn2.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn3.setBackground(getResources().getDrawable(R.mipmap.pingjia01));
                btn4.setBackground(getResources().getDrawable(R.mipmap.pingjia02));

                btn1.setTextColor(getResources().getColor(R.color.text333));
                btn2.setTextColor(getResources().getColor(R.color.text333));
                btn3.setTextColor(getResources().getColor(R.color.bgfff));
                btn4.setTextColor(getResources().getColor(R.color.text333));
                dialog.show();
                getNewsList(star);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = "3";
                btn1.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn2.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn3.setBackground(getResources().getDrawable(R.mipmap.pingjia02));
                btn4.setBackground(getResources().getDrawable(R.mipmap.pingjia01));

                btn1.setTextColor(getResources().getColor(R.color.text333));
                btn2.setTextColor(getResources().getColor(R.color.text333));
                btn3.setTextColor(getResources().getColor(R.color.text333));
                btn4.setTextColor(getResources().getColor(R.color.bgfff));
                dialog.show();
                getNewsList(star);
            }
        });


    }

    @Override
    protected void initData() {

        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            getNewsList("");
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
     * 新闻列表获取
     */
    private void getNewsList(String star) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("page", String.valueOf(p));
        params.put("gid", getIntent().getStringExtra("id"));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("star", star);
        Log.e("NewsListFragment", "params:" + params);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/goods_ping", "goods/goods_ping", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                try {
                    dialog.dismiss();
                    Log.e("NewsListFragment", decode.toString());
                    GoodsPingBean newsSearchBean = new Gson().fromJson(decode, GoodsPingBean.class);
                    if ("1".equals(String.valueOf(newsSearchBean.getStatus()))) {
                        LLEmpty.setVisibility(View.GONE);

                        btn2.setText(getString(R.string.High_praise)+"（" + newsSearchBean.getHao_ping_num() + "）");
                        btn3.setText(getString(R.string.evaluation)+"（" + newsSearchBean.getZhong_ping_num() + "）");
                        btn4.setText(getString(R.string.Bad_review)+"（" + newsSearchBean.getCha_ping_num() + "）");

                        if (ceShiLv != null) {
                            ceShiLv.setEnabled(true);
                            ceShiLv.loadMoreComplete();
                            ceShiLv.setCanloadMore(true);
                        }
                        if (p == 1) {
                            adapter = new PingJiaListAdapter(PingJiaListActivity.this, newsSearchBean);
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

}

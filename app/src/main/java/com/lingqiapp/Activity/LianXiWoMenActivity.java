package com.lingqiapp.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lingqiapp.App;
import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.Bean.LianXiBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.SpUtil;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.lingqiapp.Volley.VolleyInterface;
import com.lingqiapp.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.sakuraphonebtc.Activity
 *
 * @author 赵磊
 * @date 2018/3/31
 * 功能描述：
 */
public class LianXiWoMenActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.SimpleDraweeView1)
    SimpleDraweeView SimpleDraweeView1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.SimpleDraweeView2)
    SimpleDraweeView SimpleDraweeView2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.SimpleDraweeView3)
    SimpleDraweeView SimpleDraweeView3;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.SimpleDraweeView4)
    SimpleDraweeView SimpleDraweeView4;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.SimpleDraweeView5)
    SimpleDraweeView SimpleDraweeView5;
    @BindView(R.id.tv5)
    TextView tv5;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_lianxiwomen_layout;
    }

    @Override
    protected void initview() {

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
            getNews();
        } else {
            EasyToast.showShort(context, getString(R.string.Networkexception));
        }
    }

    /**
     * 新闻内容获取
     */
    private void getNews() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "about/lianxi" + App.LanguageTYPEHTTP, "about/lianxi", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("NewsDetailsActivity", result);
                try {
                    dialog.dismiss();
                    LianXiBean lianXiBean = new Gson().fromJson(result, LianXiBean.class);

                    if (1 == lianXiBean.getStatus()) {
                        SimpleDraweeView1.setImageURI(lianXiBean.getKfrx().getImg());

                        tv1.setText(lianXiBean.getKfrx().getTitle() + "\n" + lianXiBean.getKfrx().getContent());

                        SimpleDraweeView2.setImageURI(lianXiBean.getEmail().getImg());

                        tv2.setText(lianXiBean.getEmail().getTitle() + "\n" + lianXiBean.getEmail().getContent());


                        SimpleDraweeView3.setImageURI(lianXiBean.getWebsite().getImg());
                        tv3.setText(lianXiBean.getWebsite().getTitle() + "\n" + lianXiBean.getWebsite().getContent());

                        SimpleDraweeView4.setImageURI(lianXiBean.getWechat().getImg());

                        tv4.setText(lianXiBean.getWechat().getTitle() + "\n" + lianXiBean.getWechat().getContent());

                        SimpleDraweeView5.setImageURI(lianXiBean.getAddress().getImg());

                        tv5.setText(lianXiBean.getAddress().getTitle() + "\n" + lianXiBean.getAddress().getContent());
                    }

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("about/lianxi");
        System.gc();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

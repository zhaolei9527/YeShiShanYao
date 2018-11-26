package com.lingqiapp.Activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.R;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.Utils.Utils;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.sakuraphonebtc.Activity
 *
 * @author 赵磊
 * @date 2018/3/31
 * 功能描述：
 */
public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.forum_context)
    WebView forumContext;
    private Dialog dialog;
    private String id;


    @Override
    protected int setthislayout() {
        return R.layout.newsdetails_activity_layout;
    }

    @Override
    protected void initview() {

        id = getIntent().getStringExtra("id");


        if (TextUtils.isEmpty(id)) {
            EasyToast.showShort(context, getString(R.string.hasError));
            finish();
        }

        IX5WebViewExtension ix5 = forumContext.getX5WebViewExtension();
        // 开启 localStorage
        forumContext.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        forumContext.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        forumContext.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        forumContext.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 启动缓存
        forumContext.getSettings().setAppCacheEnabled(true);
        forumContext.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        forumContext.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                // forumContext.loadUrl("javascript:(" + readJS() + ")()");
                dialog.dismiss();
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
            }
        });


        dialog = Utils.showLoadingDialog(context);
        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        forumContext.loadUrl(UrlUtils.BASE_URL + "danye/newsdetail?nid=" + id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

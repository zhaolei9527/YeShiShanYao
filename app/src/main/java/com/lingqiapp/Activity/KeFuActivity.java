package com.lingqiapp.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.lingqiapp.Base.BaseActivity;
import com.lingqiapp.R;
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
public class KeFuActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.forum_context)
    WebView forumContext;


    @Override
    protected int setthislayout() {
        return R.layout.activity_kefu_layout;
    }

    @Override
    protected void initview() {
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            IX5WebViewExtension ix5 = forumContext.getX5WebViewExtension();
            if (null != ix5) {
                ix5.setScrollBarFadingEnabled(false);
            }
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
            forumContext.loadUrl("https://kefu.easemob.com/webim/im.html?configId=27e02ed0-94d0-4f54-a674-fd9806960849");

        }catch (Exception e){
            e.printStackTrace();
        }

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

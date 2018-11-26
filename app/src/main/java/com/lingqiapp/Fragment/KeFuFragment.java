package com.lingqiapp.Fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingqiapp.R;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class KeFuFragment extends BaseLazyFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.forum_context)
    WebView forumContext;

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
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kefu_fragment_layout, container, false);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

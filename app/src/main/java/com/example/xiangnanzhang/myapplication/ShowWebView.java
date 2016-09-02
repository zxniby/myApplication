package com.example.xiangnanzhang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by XiangnanZhang on 17/09/15.
 */
public class ShowWebView extends Activity {

    WebView mWebView;

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (WebView)findViewById(R.id.wv_page);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.163.com");
        mWebView.setWebViewClient(new WebViewClient(){
           @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               view.loadUrl(url);
               return true;
           }


        });

    }

}

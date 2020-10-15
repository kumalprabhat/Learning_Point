package com.example.learningpoint;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebView extends WebViewClient {

    public class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.ssy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        tvTitle.setText("测试");
        init();
    }

    private void init() {
        webView.loadUrl("file:///android_asset/web1.html");

        WebSettings webSettings = webView.getSettings();
        //支持JavaScript脚本
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //只要本地有，就使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //根据cache-control决定是否从网络上取数据。（可以先判断网络）
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能

        //android为js中的别名，要与html中的window.android.methodName一致
//        webView.addJavascriptInterface(new MainJSHook(getActivity()), "android");
        //设置ChromeClient
//        webView.setWebChromeClient(new HarlanWebChromeClient());
    }

    @OnClick(R.id.aivBack)
    public void onViewClicked() {
        finish();
    }

    public void changeTitleWithArgu() {
        //调用js函数并携带参数
        webView.loadUrl("javascript:javaCallJswithParam( '" + "测试带参函数" + "')");//注意格式：单引号
    }

    public void repost() {
        webView.loadUrl("javascript:forwardWeibo( '" + "转发微博" + ",5000')");
    }

    @OnClick({R.id.aivBack, R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                changeTitleWithArgu();
                break;
            case R.id.btn2:
                repost();
                break;
            case R.id.aivBack:
                finish();
                break;
        }
    }
}

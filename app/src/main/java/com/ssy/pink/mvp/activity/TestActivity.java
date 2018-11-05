package com.ssy.pink.mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ssy.pink.R;
import com.ssy.pink.bean.weibo.PreLoginInfo;
import com.ssy.pink.glide.GlideUtils;
import com.ssy.pink.network.api.sina.SinaSSO;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.etAccout)
    EditText etAccout;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    PreLoginInfo preLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnPre, R.id.btnLogin, R.id.btnCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPre:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        preLoginInfo = SinaSSO.getInstance().getServerInfo();
                        Log.i("aaaa", "预登陆：" + preLoginInfo.toString());
                    }
                }).start();
                break;
            case R.id.btnLogin:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (preLoginInfo != null) {
//                            WeiboLoginInfo weiboLoginInfo = SinaSSO.getInstance().testLogin(preLoginInfo, etAccout.getText().toString(), etPwd.getText().toString(), etCode.getText().toString());
//                            Log.i("aaaa", "登陆：" + weiboLoginInfo.toString());
                        }

                    }
                }).start();

                break;
            case R.id.btnCode:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (preLoginInfo != null) {
                            final File file = SinaSSO.getInstance().downloadImage(preLoginInfo);
//                            Tesseract instance = new Tesseract();

                            //将验证码图片的内容识别为字符串
//                            try {
//                                String result = instance.doOCR(file);
//                                Log.i("aaaa", "result:" + result);
//                            } catch (TesseractException e) {
//                                Log.i("aaaa", "result TesseractException:" + e.getMessage());
//                                e.printStackTrace();
//                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GlideUtils.loadImageWithoutCache(TestActivity.this, ivCode, file);
                                }
                            });
                        }
                    }
                }).start();
                break;
        }
    }
}

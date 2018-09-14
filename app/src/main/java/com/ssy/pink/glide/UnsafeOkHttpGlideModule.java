package com.ssy.pink.glide;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.ssy.pink.R;
import com.ssy.pink.network.OkHttpClientProvider;

import java.io.InputStream;

/**
 * 跳过证书验证
 * Created by ssy on 2017/5/16.
 */

public class UnsafeOkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientProvider.getWeiboClient()));
    }
}

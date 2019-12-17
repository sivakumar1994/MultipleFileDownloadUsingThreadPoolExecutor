package com.example.threadpoolexectordemo.utils;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.threadpoolexectordemo.MyApplication;

public class CustomUtils {

    private static CustomUtils instance;

    public static CustomUtils getInstance(){
        if(instance == null)
            return instance = new CustomUtils();
        else
            return instance;
    }

    public void loadImage(String url, ImageView imageView) {
        Glide.with(MyApplication.getAppContext()).asBitmap().apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
            .load(url).into(new BitmapImageViewTarget(imageView));
    }
}

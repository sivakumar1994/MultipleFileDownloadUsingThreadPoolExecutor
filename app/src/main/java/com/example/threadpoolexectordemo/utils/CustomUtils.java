package com.example.threadpoolexectordemo.utils;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.threadpoolexectordemo.MyApplication;

public class CustomUtils {

    private static CustomUtils instance;
    public static final String MESSAGE_BODY = "MESSAGE_BODY";
    public static final int MESSAGE_ID = 1;
    public static final int MESSAGE_ID_FOR_PROGRESS = 2;

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

    public static Message createMessage(int id,int position, String dataString) {
        Bundle bundle = new Bundle();
        bundle.putString(CustomUtils.MESSAGE_BODY, dataString);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }

    public static Message createMessage(int id,int position, int dataInt) {
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putInt(CustomUtils.MESSAGE_BODY, dataInt);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }
}

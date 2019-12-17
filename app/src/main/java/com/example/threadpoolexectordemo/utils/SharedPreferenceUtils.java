package com.example.threadpoolexectordemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.threadpoolexectordemo.MyApplication;
import com.example.threadpoolexectordemo.R;

public final class SharedPreferenceUtils {
    private static SharedPreferenceUtils instance;
    private SharedPreferences sharedPreferences;

    public static SharedPreferenceUtils getInstance() {
        if (instance == null)
         return instance = new SharedPreferenceUtils();
        else
            return instance;
    }

    private SharedPreferenceUtils(){
        sharedPreferences = MyApplication.getAppContext()
                .getSharedPreferences(MyApplication.getAppContext().getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    private void storeValue(String key, String value) {
        sharedPreferences.edit().putString(key,value).apply();
    }

    private void getStringValue(String key,  String defaultValue) {
        sharedPreferences.getString(key,defaultValue);
    }
}

package com.example.threadpoolexectordemo;

import android.os.Message;

public interface UiThreadCallback {
    void publishToUiThread(Message message);
    void updateProgress(Message message);
}

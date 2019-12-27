package com.example.threadpoolexectordemo.filedownloading.view;

import com.example.threadpoolexectordemo.backgroundservice.ThreadPoolManager;
import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

public interface FileDownloadingView {
    void showDownloadFile(List<FileDetails> fileDetails);
    void showToast(String message);
    FileDownloadingAdapter getAdapter();
    ThreadPoolManager getThreadPoolManager();
}

package com.example.threadpoolexectordemo.filedownloading;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

public interface FileDownloadingView {
    void showDownloadFile(List<FileDetails> fileDetails);
    void showToast(String message);
}

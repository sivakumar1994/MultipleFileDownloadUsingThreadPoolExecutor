package com.example.threadpoolexectordemo.filedownloading.presenter;

import android.os.Message;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

public interface FileDownloadingPresenter {
     void loadFile();
     void showErrorMessage(String message);
     void setFileDetails(List<FileDetails> fileDetails);
     void downloadAllFile();
     void updateProgress(Message message);
}

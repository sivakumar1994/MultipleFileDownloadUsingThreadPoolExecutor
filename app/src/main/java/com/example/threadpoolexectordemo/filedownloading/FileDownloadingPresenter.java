package com.example.threadpoolexectordemo.filedownloading;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

public interface FileDownloadingPresenter {
     void loadFile();
     void showErrorMessage(String message);
     void setFileDetails(List<FileDetails> fileDetails);
     void DownloadAllFile();
}

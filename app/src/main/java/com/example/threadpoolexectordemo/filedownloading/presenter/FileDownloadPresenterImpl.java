package com.example.threadpoolexectordemo.filedownloading.presenter;

import android.os.Bundle;
import android.os.Message;

import com.example.threadpoolexectordemo.backgroundservice.DownloadCallback;
import com.example.threadpoolexectordemo.filedownloading.model.FileDownloadRepo;
import com.example.threadpoolexectordemo.filedownloading.view.FileDownloadingView;
import com.example.threadpoolexectordemo.model.FileDetails;
import com.example.threadpoolexectordemo.utils.CustomUtils;

import java.util.List;

public class FileDownloadPresenterImpl implements FileDownloadingPresenter {

    FileDownloadRepo fileDownloadRepo = new FileDownloadRepo(this);
    private FileDownloadingView fileDownloadingView;
    private List<FileDetails> fileDetails;

    public FileDownloadPresenterImpl(FileDownloadingView fileDownloadingView) {
        this.fileDownloadingView = fileDownloadingView;
    }

    @Override
    public void loadFile() {
        fileDownloadRepo.loadFileData();
    }

    @Override
    public void showErrorMessage(String message) {
        fileDownloadingView.showToast(message);
    }

    @Override
    public void setFileDetails(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
        fileDownloadingView.showDownloadFile(fileDetails);
    }

    @Override
    public void downloadAllFile() {
       // fileDownloadingView.getAdapter().downloadAll();
        int i=0;
        for (FileDetails fileDetails : fileDetails) {
            if(!fileDetails.isDownloaded()) {
                DownloadCallback downloadCallback = new DownloadCallback(fileDownloadingView.getThreadPoolManager(), fileDetails.getVideoUrl(), i);
                //downloadCallback.onDownloadCallback(fileDetails.getVideoUrl());
                fileDownloadingView.getThreadPoolManager().addCallable(downloadCallback);
            }
            i++;
        }
    }

    public void updateProgress(Message message) {
        Bundle bundle = message.getData();
        int position = bundle.getInt("position");
        int value = bundle.getInt(CustomUtils.MESSAGE_BODY);
        fileDownloadingView.getAdapter().getFileDetails().get(position).setSeekBarValue(value);
        fileDownloadingView.getAdapter().notifyItemChanged(position);
    }
}

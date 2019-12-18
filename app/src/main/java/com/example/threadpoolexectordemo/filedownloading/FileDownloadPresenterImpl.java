package com.example.threadpoolexectordemo.filedownloading;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

public class FileDownloadPresenterImpl implements FileDownloadingPresenter {

    private FileDownloadingView fileDownloadingView;

    public FileDownloadPresenterImpl(FileDownloadingView fileDownloadingView) {
        this.fileDownloadingView =fileDownloadingView;
    }
    FileDownloadRepo fileDownloadRepo = new FileDownloadRepo(this);
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
        fileDownloadingView.showDownloadFile(fileDetails);
    }

    @Override
    public void DownloadAllFile() {
        //todo in the functionality integration
    }


}

package com.example.threadpoolexectordemo.downloaded.presenter;

import com.example.threadpoolexectordemo.downloaded.view.FileDownloadedView;
import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

public class FileDownloadedPresenterImpl implements DownloadedFragmentPresenter {

    private FileDownloadedView fileDownloadedView;

    public FileDownloadedPresenterImpl(FileDownloadedView fileDownloadedView) {
        this.fileDownloadedView = fileDownloadedView;
    }

    @Override
    public void setData(List<FileDetails> fileDetails) {
        fileDownloadedView.setFileDownloadedAdapterData(fileDetails);
    }
}

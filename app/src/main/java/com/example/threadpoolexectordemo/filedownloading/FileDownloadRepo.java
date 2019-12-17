package com.example.threadpoolexectordemo.filedownloading;

import com.example.threadpoolexectordemo.model.FileDetails;
import com.example.threadpoolexectordemo.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileDownloadRepo {

    private FileDownloadPresenterImpl fileDownloadPresenter;

    public FileDownloadRepo(FileDownloadPresenterImpl fileDownloadPresenter) {
        this.fileDownloadPresenter = fileDownloadPresenter;
    }

    public void loadFileData() {
        RestClient.getInstance().get().getFileDetails().enqueue(new Callback<List<FileDetails>>() {
            @Override
            public void onResponse(Call<List<FileDetails>> call, Response<List<FileDetails>> response) {
                fileDownloadPresenter.setFileDetails(response.body());
            }

            @Override
            public void onFailure(Call<List<FileDetails>> call, Throwable t) {
                fileDownloadPresenter.showErrorMessage(t.getMessage());
            }
        });
    }
}

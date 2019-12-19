package com.example.threadpoolexectordemo.rest;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("5dfb84b12f00006800ff9ff4")
    Call<List<FileDetails>> getFileDetails();
}

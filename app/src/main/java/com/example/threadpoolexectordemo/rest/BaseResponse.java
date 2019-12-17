package com.example.threadpoolexectordemo.rest;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

public class BaseResponse {
    List<FileDetails> fileDetails;

    public List<FileDetails> getFileDetails() {
        return fileDetails;
    }
}

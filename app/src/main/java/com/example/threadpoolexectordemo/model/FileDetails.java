package com.example.threadpoolexectordemo.model;

import com.google.gson.annotations.SerializedName;

public class FileDetails {
    private String id;

    @SerializedName("tumbnailImage")
    private String thumbnailImage;

    private String videoUrl;

    public String getId() {
        return id;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}

package com.example.threadpoolexectordemo.model;

import com.google.gson.annotations.SerializedName;

public class FileDetails {
    private String id;

    @SerializedName("tumbnailImage")
    private String thumbnailImage;

    private String videoUrl;

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int seekBarValue;

    public int getSeekBarValue() {
        return seekBarValue;
    }

    public void setSeekBarValue(int seekBarValue) {
        this.seekBarValue = seekBarValue;
    }

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

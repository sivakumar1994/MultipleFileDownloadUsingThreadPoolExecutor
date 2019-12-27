package com.example.threadpoolexectordemo.model;

import com.google.gson.annotations.SerializedName;

public class FileDetails {
    private String id;

    @SerializedName("tumbnailImage")
    private String thumbnailImage;

    private String videoUrl;

    private int index;

    private boolean isDownloaded = false;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
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

package com.example.threadpoolexectordemo.filedownloading;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.threadpoolexectordemo.DownloadTask;
import com.example.threadpoolexectordemo.R;
import com.example.threadpoolexectordemo.model.FileDetails;
import com.example.threadpoolexectordemo.utils.CustomUtils;

import java.util.ArrayList;
import java.util.List;

public class FileDownloadingAdapter extends RecyclerView.Adapter<FileDownloadingAdapter.MyViewHolder> {

    private List<FileDetails> fileDetails = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_file_downloading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvFileName.setText(fileDetails.get(position).getVideoUrl());
        CustomUtils.getInstance().loadImage(fileDetails.get(position).getThumbnailImage(),holder.imageView);
        holder.seekBar.setProgress(fileDetails.get(position).getSeekBarValue());
    }

    @Override
    public int getItemCount() {
        return fileDetails.size();
    }

    public void setData(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
        notifyDataSetChanged();
    }
    public void downloadAll() {

        int i=0;
        for(FileDetails fileDetail : fileDetails ) {
            DownloadTask downloadTask = new DownloadTask(this,fileDetails);
            fileDetail.setIndex(i);
            i++;
           downloadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, fileDetail);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFileName;
        private ImageView imageView;
        private SeekBar seekBar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tv_file_name);
            imageView =itemView.findViewById(R.id.img_thumbnail);
            seekBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}

package com.example.threadpoolexectordemo.downloaded.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.threadpoolexectordemo.R;
import com.example.threadpoolexectordemo.model.FileDetails;
import com.example.threadpoolexectordemo.utils.CustomUtils;

import java.util.ArrayList;
import java.util.List;

public class DownloadedFileRVAdapter extends RecyclerView.Adapter<DownloadedFileRVAdapter.MyViewHolder> {

    private List<FileDetails> fileDetails = new ArrayList<>();

    public DownloadedFileRVAdapter(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_downloaded_file,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(fileDetails.get(position).isDownloaded()) {
            holder.tvFileName.setText(fileDetails.get(position).getVideoUrl());
            CustomUtils.getInstance().loadImage(fileDetails.get(position).getThumbnailImage(), holder.imgThumbnailImage);
        }
    }

    public void setFileDetails(List<FileDetails> fileDetails){
        this.fileDetails =fileDetails;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return fileDetails.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFileName;
        private ImageView imgThumbnailImage;
         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName =itemView.findViewById(R.id.tv_file_name);
            imgThumbnailImage = itemView.findViewById(R.id.img_thumbnail);
        }
    }
}

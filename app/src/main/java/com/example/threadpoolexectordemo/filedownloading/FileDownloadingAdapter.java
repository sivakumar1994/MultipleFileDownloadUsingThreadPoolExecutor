package com.example.threadpoolexectordemo.filedownloading;

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
    }

    @Override
    public int getItemCount() {
        return fileDetails.size();
    }

    public void setData(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFileName;
        private ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tv_file_name);
            imageView =itemView.findViewById(R.id.img_thumbnail);
        }
    }
}

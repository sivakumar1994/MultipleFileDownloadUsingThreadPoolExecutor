package com.example.threadpoolexectordemo.filedownloading.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.threadpoolexectordemo.DownloadTask;
import com.example.threadpoolexectordemo.R;
import com.example.threadpoolexectordemo.filedownloading.FileDownloadPresenterImpl;
import com.example.threadpoolexectordemo.filedownloading.FileDownloadingAdapter;
import com.example.threadpoolexectordemo.filedownloading.FileDownloadingView;
import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;


public class FileDownloadingFragment extends Fragment implements FileDownloadingView, View.OnClickListener {


    private static final int EXTERNAL_WRITE_REQ = 010;
    private FileDownloadPresenterImpl fileDownloadingPresenter;
    private RecyclerView recyclerView;
    private FileDownloadingAdapter fileDownloadingAdapter;
    private Button btnDownloadAll;
    private boolean isPermissionGranted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file_downloading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fileDownloadingPresenter = new FileDownloadPresenterImpl(this);
        initView(view);
        setRecyclerView();
        setListener();
        fileDownloadingPresenter.loadFile();
        checkPermission();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        btnDownloadAll = view.findViewById(R.id.btn_download_all);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fileDownloadingAdapter = new FileDownloadingAdapter();
        recyclerView.setAdapter(fileDownloadingAdapter);
    }

    private void setListener() {
        btnDownloadAll.setOnClickListener(this);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = false;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_WRITE_REQ);

        } else
            isPermissionGranted = true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case EXTERNAL_WRITE_REQ:
                if (resultCode == Activity.RESULT_OK)
                    isPermissionGranted = true;
                else
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void showDownloadFile(List<FileDetails> fileDetails) {
        fileDownloadingAdapter.setData(fileDetails);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download_all:
               // if (isPermissionGranted) {
                   fileDownloadingAdapter.downloadAll();
                //}

              //  else
                //    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}

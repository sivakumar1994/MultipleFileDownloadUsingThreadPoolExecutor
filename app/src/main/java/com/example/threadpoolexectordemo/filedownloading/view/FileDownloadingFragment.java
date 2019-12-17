package com.example.threadpoolexectordemo.filedownloading.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.threadpoolexectordemo.R;
import com.example.threadpoolexectordemo.filedownloading.FileDownloadPresenterImpl;
import com.example.threadpoolexectordemo.filedownloading.FileDownloadingAdapter;
import com.example.threadpoolexectordemo.filedownloading.FileDownloadingView;
import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;


public class FileDownloadingFragment extends Fragment implements FileDownloadingView {

    private static FileDownloadingFragment instance;
    private FileDownloadPresenterImpl fileDownloadingPresenter;
    private RecyclerView recyclerView;
    private  FileDownloadingAdapter fileDownloadingAdapter;

    public static FileDownloadingFragment newInstance() {
        if (instance == null)
            return instance = new FileDownloadingFragment();
        else
            return instance;
    }

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
        fileDownloadingPresenter.loadFile();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         fileDownloadingAdapter = new FileDownloadingAdapter();
        recyclerView.setAdapter(fileDownloadingAdapter);
    }

    @Override
    public void showDownloadFile(List<FileDetails> fileDetails) {
        fileDownloadingAdapter.setData(fileDetails);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

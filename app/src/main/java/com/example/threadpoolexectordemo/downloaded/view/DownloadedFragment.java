package com.example.threadpoolexectordemo.downloaded.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.threadpoolexectordemo.R;
import com.example.threadpoolexectordemo.downloaded.presenter.FileDownloadedPresenterImpl;
import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.ArrayList;
import java.util.List;

public class DownloadedFragment extends Fragment implements FileDownloadedView {


private DownloadedFileRVAdapter downloadedFileRVAdapter = new DownloadedFileRVAdapter(new ArrayList<FileDetails>());
private FileDownloadedPresenterImpl fileDownloadedPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_downloaded, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fileDownloadedPresenter = new FileDownloadedPresenterImpl(this);
        RecyclerView recyclerView =view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(downloadedFileRVAdapter);
    }

    public void receivedData(List<FileDetails> fileDetails) {
       fileDownloadedPresenter.setData(fileDetails);
    }

    @Override
    public void setFileDownloadedAdapterData(List<FileDetails> fileDetailsList) {
        downloadedFileRVAdapter.setFileDetails(fileDetailsList);
    }
}

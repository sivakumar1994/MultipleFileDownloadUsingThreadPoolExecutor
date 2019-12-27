package com.example.threadpoolexectordemo.filedownloading.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.threadpoolexectordemo.R;
import com.example.threadpoolexectordemo.backgroundservice.ThreadPoolManager;
import com.example.threadpoolexectordemo.UiThreadCallback;
import com.example.threadpoolexectordemo.filedownloading.presenter.FileDownloadPresenterImpl;
import com.example.threadpoolexectordemo.model.FileDetails;
import com.example.threadpoolexectordemo.utils.CustomUtils;

import java.lang.ref.WeakReference;
import java.util.List;


public class FileDownloadingFragment extends Fragment implements FileDownloadingView, View.OnClickListener, UiThreadCallback {


    private static final int EXTERNAL_WRITE_REQ = 010;
    private FileDownloadPresenterImpl fileDownloadingPresenter;
    private RecyclerView recyclerView;
    private FileDownloadingAdapter fileDownloadingAdapter;
    private Button btnDownloadAll;
    private boolean isPermissionGranted;
    private ThreadPoolManager threadPoolManager;

    private UiHandler uiHandler;

    private SendCompletedFileDetails sendCompletedFileDetails;

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
        threadPoolManager = ThreadPoolManager.getInstance();
        threadPoolManager.setUiThreadCallBack(this);
        uiHandler = new UiHandler(Looper.getMainLooper(), getContext(), fileDownloadingAdapter, sendCompletedFileDetails);
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
                break;
            default:
                break;

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
    public FileDownloadingAdapter getAdapter() {
        return fileDownloadingAdapter;
    }

    @Override
    public ThreadPoolManager getThreadPoolManager() {
        return threadPoolManager;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download_all:
                // if (isPermissionGranted) {
                //fileDownloadingAdapter.downloadAll();
//                DownloadCallback downloadCallback = new DownloadCallback(threadPoolManager);
//                downloadCallback.downloadCallback();
                //}
                fileDownloadingPresenter.downloadAllFile();

                //  else
                //    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public void publishToUiThread(Message message) {
        if (uiHandler != null)
            uiHandler.sendMessage(message);
    }

    @Override
    public void updateProgress(Message message) {
        if (uiHandler != null)
            uiHandler.sendMessage(message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendCompletedFileDetails = (SendCompletedFileDetails) getActivity();
    }

    public interface SendCompletedFileDetails {
        void sendList(List<FileDetails> fileDetails);
    }

    // UI handler class, declared as static so it doesn't have implicit
    // reference to activity context. This helps to avoid memory leak.
    private static class UiHandler extends Handler {
        SendCompletedFileDetails sendCompletedFileDetails;
        private WeakReference<TextView> mWeakRefDisplay;
        private Context context;
        private FileDownloadingAdapter fileDownloadingAdapter;

        UiHandler(Looper looper, Context context, FileDownloadingAdapter fileDownloadingAdapter, SendCompletedFileDetails sendCompletedFileDetails) {
            super(looper);
            this.context = context;
            // this.mWeakRefDisplay = new WeakReference<TextView>(display);
            this.fileDownloadingAdapter = fileDownloadingAdapter;
            this.sendCompletedFileDetails =sendCompletedFileDetails;
        }

        // This method will run on UI thread
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int position = bundle.getInt("position");
            switch (msg.what) {
                // Our communication protocol for passing a string to the UI thread
                case CustomUtils.MESSAGE_ID:
                    //    Bundle bundle = msg.getData();
                    //    String messsageText = bundle.getString(CustomUtils.MESSAGE_BODY, "empty");
//                    if(mWeakRefDisplay != null && mWeakRefDisplay.get() != null)
////                        mWeakRefDisplay.get().append(Util.getReadableTime() + " " + messsageText + "\n");
////
                    // Toast.makeText(context, messsageText, Toast.LENGTH_SHORT).show();

                    List<FileDetails> fileDetailsList;
                    fileDetailsList = fileDownloadingAdapter.getFileDetails();

                    fileDetailsList.get(position).setDownloaded(true);
                   // fileDownloadingAdapter.getFileDetails().remove(position);
                    sendCompletedFileDetails.sendList(fileDetailsList);
                    fileDownloadingAdapter.notifyDataSetChanged();

                    break;
                case CustomUtils.MESSAGE_ID_FOR_PROGRESS:
                    int progressValue = bundle.getInt(CustomUtils.MESSAGE_BODY, 0);
                    Toast.makeText(context, " " + progressValue, Toast.LENGTH_SHORT).show();
                    int value = bundle.getInt(CustomUtils.MESSAGE_BODY);
                    fileDownloadingAdapter.getFileDetails().get(position).setSeekBarValue(value);
                    fileDownloadingAdapter.notifyItemChanged(position);
                default:
                    break;
            }
        }
    }
}

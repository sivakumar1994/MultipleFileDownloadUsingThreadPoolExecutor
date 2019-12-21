package com.example.threadpoolexectordemo;

import android.os.AsyncTask;
import android.util.Log;

import com.example.threadpoolexectordemo.filedownloading.FileDownloadingAdapter;
import com.example.threadpoolexectordemo.model.FileDetails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadTask extends AsyncTask<FileDetails, Integer, String> {

    String folderPath = "/storage/emulated/0/MyVideo/";
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream = null;
    private FileOutputStream outputStream = null;
    private int i = -1;
    private List<FileDetails> fileDetails;

    private FileDownloadingAdapter fileDownloadingAdapter;

    public DownloadTask(FileDownloadingAdapter fileDownloadingAdapter, List<FileDetails> fileDetails) {

        this.fileDownloadingAdapter = fileDownloadingAdapter;
        this.fileDetails = fileDetails;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        fileDetails.get(i).setSeekBarValue(values[0]);
        Log.v("test","progress bar update position: "+ i);
        fileDownloadingAdapter.notifyDataSetChanged();
    }

    @Override
    protected String doInBackground(FileDetails... fileDetails) {
            try {
                i=fileDetails[0].getIndex();
                URL url = new URL(fileDetails[0].getVideoUrl());
                int len = fileDetails[0].getVideoUrl().lastIndexOf("/");
                String fileName =fileDetails[0].getVideoUrl().substring(len);
                String filePath = folderPath + fileName;
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
                    return "Server return " + httpURLConnection.getResponseCode() + httpURLConnection.getResponseMessage();
                int fileLength = httpURLConnection.getContentLength();

                File file = new File(filePath);
                File parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }

                inputStream = httpURLConnection.getInputStream();
                outputStream = new FileOutputStream(file);
                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = inputStream.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        inputStream.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    outputStream.write(data, 0, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null)
                        outputStream.close();
                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException ingnored) {

                }
            }
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        return null;
    }
}

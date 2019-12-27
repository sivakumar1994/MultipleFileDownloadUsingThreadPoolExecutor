package com.example.threadpoolexectordemo.backgroundservice;

import com.example.threadpoolexectordemo.utils.CustomUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class DownloadCallback implements Callable {

    private static final String FOLDER_PATH = "/storage/emulated/0/MyVideo/";
    private String imageUrl;
    private int position;
    private WeakReference<ThreadPoolManager> threadPoolManagerWeakReference;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream = null;
    private FileOutputStream outputStream = null;
    private int i = -1;

    public DownloadCallback(ThreadPoolManager threadPoolManagerWeakReference, String imageUrl, int position) {
        this.threadPoolManagerWeakReference = new WeakReference<ThreadPoolManager>(threadPoolManagerWeakReference);
        this.imageUrl = imageUrl;
        this.position = position;
    }

    private void onDownloadCallback(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            int len = imageUrl.lastIndexOf("/");
            String fileName = imageUrl.substring(len);
            String filePath = FOLDER_PATH + fileName;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {

                threadPoolManagerWeakReference.get().sendMsgToUiThread(CustomUtils.createMessage(CustomUtils.MESSAGE_ID, position,
                        "Server return " + httpURLConnection.getResponseCode() + httpURLConnection.getResponseMessage()));
                return;
            }
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
                if (Thread.currentThread().isInterrupted()) {
                    inputStream.close();
                    return;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) {
                    int progressValue = (int) (total * 100 / fileLength);
                    threadPoolManagerWeakReference.get().sendProgressToUiThread(CustomUtils.createMessage(CustomUtils.MESSAGE_ID_FOR_PROGRESS, position, progressValue));
                }
                outputStream.write(data, 0, count);
            }
            threadPoolManagerWeakReference.get().sendMsgToUiThread(CustomUtils.createMessage(CustomUtils.MESSAGE_ID, position, "Completed"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException ignore) {

            }
        }
        if (httpURLConnection != null)
            httpURLConnection.disconnect();

    }


    @Override
    public Object call() throws Exception {
        onDownloadCallback(imageUrl);
        return null;
    }
}

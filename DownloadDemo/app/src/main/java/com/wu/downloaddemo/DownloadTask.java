package com.wu.downloaddemo;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wu on 2017/8/24.
 */

public class DownloadTask extends AsyncTask<String,Integer,Integer> {
    private static final int TYPE_SUCCESSED = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 4;
    private DownloadListener listener;
    private boolean isPaused;
    private boolean isCanceled;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        RandomAccessFile saveFile = null;
        InputStream is = null;
        long downloadLength = 0;
        File file=null;
        try {
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if(file.exists()){
                downloadLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if(contentLength==0){
                return TYPE_FAILED;
            }else if(contentLength==downloadLength){
                return TYPE_SUCCESSED;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(downloadUrl).addHeader("RANGE", "bytes=" + downloadLength + "-").build();
            Response response = client.newCall(request).execute();
            if(response!=null&&response.isSuccessful()){
                is = response.body().byteStream();
                saveFile = new RandomAccessFile(file,"rw");
                saveFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int len;
                int total=0;
                int progress = 0;
                while ((len=is.read(b))!=-1){
                    if(isCanceled){
                        return TYPE_CANCELED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else{
                        saveFile.read(b, 0, len);
                        total += len;
                        progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESSED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null){
                    is.close();
                }
                if(saveFile!=null){
                    saveFile.close();
                }
                if(isCanceled&&file!=null){
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress>lastProgress){
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch(integer){
            case TYPE_SUCCESSED:
                listener.onSuccessed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            default:
                break;
        }
    }
    private long getContentLength(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if(response!=null&&response.isSuccessful()){
            long length = response.body().contentLength();
            response.close();
            return length;
        }
        return 0;
    }
    public void pauseDownload(){
        isPaused = true;
    }
    public void cancelDownload(){
        isCanceled = true;
    }
}

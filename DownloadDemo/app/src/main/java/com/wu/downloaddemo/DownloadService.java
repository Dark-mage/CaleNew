package com.wu.downloaddemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask downloadTask;
    private String downloadUrl;
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
           getNotificationManager().notify(1, getNotification("downloading...", progress));
        }

        @Override
        public void onSuccessed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("downloading success", -1));
            Toast.makeText(DownloadService.this, "download success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("downloading failed", -1));
            Toast.makeText(DownloadService.this, "download failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "download paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "download failed", Toast.LENGTH_SHORT).show();
        }
    };
    public DownloadService() {
    }


    private DownloadBinder downloadBinder = new DownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }
    class DownloadBinder extends Binder{
        public void startDownload(String url){
            if(downloadTask==null){
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1,getNotification("start download", 0));
                Toast.makeText(DownloadService.this, "download start", Toast.LENGTH_SHORT).show();
            }
        }
        public void pauseDownload(){
            if(downloadTask!=null){
                downloadTask.pauseDownload();
            }

        }
        public void cancelDownload(){
            if(downloadTask!=null){
                downloadTask.cancelDownload();
            }else{
                if(downloadUrl!=null){
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "download cancel", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    private Notification getNotification(String title,int progress){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle(title);
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        if(progress>=0){
            builder.setProgress(100, 0, false);
            builder.setContentText(progress + "%");
        }
        return builder.build();

    }
}

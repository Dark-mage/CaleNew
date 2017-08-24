package com.wu.downloaddemo;

/**
 * Created by wu on 2017/8/24.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccessed();

    void onFailed();

    void onPaused();

    void onCanceled();
}

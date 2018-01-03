package com.alizhezi.demo.imageload;

import android.net.Uri;

import com.squareup.picasso.Downloader;

import java.io.IOException;

/**
 * Created by gavin
 * Time 2017/12/25  14:40
 * Email:molu_clown@163.com
 */

public class MyDownloader implements Downloader {
    @Override
    public Response load(Uri uri, int networkPolicy) throws IOException {
        return null;
    }

    @Override
    public void shutdown() {

    }
}

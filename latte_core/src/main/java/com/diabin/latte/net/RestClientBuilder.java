package com.diabin.latte.net;

import android.content.Context;

import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.IRequest;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by admin on 2019/3/18.
 * 建造者模式
 */

public class RestClientBuilder {

    private String url = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest request = null;
    private ISuccess success = null;
    private IError error = null;
    private IFailure failure = null;
    private RequestBody body = null;
    private LoaderStyle loaderStyle = null;
    private Context context = null;
    private File file = null;

    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.url = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, String value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.file = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.file = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.success = iSuccess;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.error = iError;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.failure = iFailure;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.request = iRequest;
        return this;
    }

    public final RestClientBuilder loader(LoaderStyle loaderStyle, Context context) {
        this.loaderStyle = loaderStyle;
        this.context = context;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.loaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        this.context = context;
        return this;
    }

    public final RestClient build() {
        return new RestClient(url, PARAMS, request, success, error, failure, body, loaderStyle, context, file, mDownloadDir, mExtension, mName);
    }
}

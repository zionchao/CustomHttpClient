package com.kevin.http;

import java.net.HttpURLConnection;

/**
 * Created by ZhangChao on 2016/9/18.
 */
public interface ICallback<T> {

    void onSuccess(T result);

    void onFailuer(AppException error);

    T onPostRequest(T t);

    T onPreRequest();

    T parseResponse(HttpURLConnection connection) throws AppException;

    T parseResponse(HttpURLConnection connection,OnProgressUpdateListener listener) throws AppException;

    void onProgressUpdata(int curLen, int totalLen);

    void cancle();


}

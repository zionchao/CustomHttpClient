package com.kevin.http;

import java.net.HttpURLConnection;

/**
 * Created by ZhangChao on 2016/9/18.
 */
public interface ICallback<T> {
    void onSuccess(T result);
    void onFailuer(Exception error);

    T parseResponse(HttpURLConnection connection) throws Exception;
}

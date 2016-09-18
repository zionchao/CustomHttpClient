package com.kevin.http;

/**
 * Created by ZhangChao on 2016/9/18.
 */
public interface ICallback {
    void onSuccess(String result);
    void onFailuer(Exception error);
}

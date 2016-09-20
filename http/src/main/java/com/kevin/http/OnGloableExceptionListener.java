package com.kevin.http;

/**
 * Created by ZhangChao on 2016/9/20.
 */

public interface OnGloableExceptionListener {
    boolean handleException(AppException e);
}

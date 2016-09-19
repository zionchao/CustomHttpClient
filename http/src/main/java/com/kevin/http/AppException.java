package com.kevin.http;

/**
 * Created by ZhangChao on 2016/9/19.
 */
public class AppException extends Exception{

    public int status;
    public String responseMessage;

    public AppException(String message) {
        super(message);
    }

    public AppException(int status, String responseMessage) {
        super(responseMessage);
        this.status=status;
        this.responseMessage=responseMessage;
    }
}

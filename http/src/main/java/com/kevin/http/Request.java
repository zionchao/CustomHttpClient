package com.kevin.http;

import java.util.Map;

/**
 * Created by zhangchao_a on 2016/9/18.
 */
public class Request {

    public ICallback iCallback;
    public boolean enableProgressUpdate;
    public OnGloableExceptionListener onGloableExceptionListener;
    public int maxRetryTime=3;
    public boolean isCancleHttp;
    public String tag;

    public int getMaxRetryTime() {
        return maxRetryTime;
    }
    public void setMaxRetryTime(int maxRetryTime) {
        this.maxRetryTime = maxRetryTime;
    }

    public void enableProgressUpdate(boolean enable) {
        this.enableProgressUpdate=enable;
    }

    public void setOnGloableListener(OnGloableExceptionListener onGloableExptionListener) {
        this.onGloableExceptionListener=onGloableExptionListener;
    }

    public void checkIsCancle() throws AppException {
        if (isCancleHttp)
            throw new AppException(AppException.ErrorType.CANCLE_HTTP,"取消HTTP请求");
    }

    public void cancle(boolean isCancle)
    {
        this.isCancleHttp=isCancle;
        this.iCallback.cancle();
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public enum RequestMethod{
        GET,POST,PUT,DELETE
    }
    public  String url;
    public  Map<String,String> headers;
    public  String content;
    public  RequestMethod method;


    public  Request(String url)
    {
        this.url=url;
        this.method= RequestMethod.GET;
    }

    public  Request(String url,RequestMethod method)
    {
        this.url=url;
        this.method=method;
    }

    public void setCallback(ICallback iCallback)
    {
        this.iCallback=iCallback;
    }

}

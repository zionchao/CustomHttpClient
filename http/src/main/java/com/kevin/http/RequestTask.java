package com.kevin.http;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by ZhangChao on 2016/9/18.
 */
public class RequestTask extends AsyncTask {

    private Request request;

    public RequestTask(Request request) {
        this.request=request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return request(0);
    }

    public Object request(int retryTime)
    {
        try {
            HttpURLConnection connection= HttpUrlConnectionUtil.exec(request);
            if (request.enableProgressUpdate)
            {
                return request.iCallback.parseResponse(connection,new OnProgressUpdateListener(){
                    @Override
                    public void onProgressUpdate(int curLen, int totalLen) {
                        publishProgress(curLen,totalLen);
                    }
                });
            }else
                return request.iCallback.parseResponse(connection);

        } catch (AppException e) {
            if (e.type== AppException.ErrorType.TIMEOUT)
            {
                if(retryTime<request.maxRetryTime) {
                    retryTime++;
                    return request(retryTime);
                }
            }
            return e;
        }
    }
    @Override
    protected void onProgressUpdate(Object[] values) {
        request.iCallback.onProgressUpdata((int)values[0],(int)values[1]);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof AppException)
            if(request.onGloableExceptionListener!=null)
            {
                if(!request.onGloableExceptionListener.handleException((AppException) o))
                {
                    request.iCallback.onFailuer((AppException) o);
                }
            }
        else
            request.iCallback.onSuccess(o);
    }
}

package com.kevin.http;

import android.os.AsyncTask;

import java.io.IOException;

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
        try {
            return HttpUrlConnectionUtil.exec(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception)
            request.iCallback.onFailuer((Exception) o);
        else
             request.iCallback.onSuccess((String) o);
    }
}

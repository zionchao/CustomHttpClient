package com.kevin.http;

import android.webkit.URLUtil;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import okhttp3.internal.huc.OkHttpURLConnection;
import okhttp3.internal.huc.OkHttpsURLConnection;


/**
 * Created by ZhangChao on 2016/9/23.
 */

public class OkHttpUrlConnectionUtil {

    public static OkHttpURLConnection exec(Request request) throws AppException {

        if(!URLUtil.isNetworkUrl(request.url))
        {
            throw new AppException(AppException.ErrorType.SERVER,"url:"+request.url+"is not valid");
        }
        switch (request.method)
        {
            case GET:
                return get(request);

            case POST:
                return post(request);
        }
        return null;
    }

    public static OkHttpURLConnection get(Request request) throws AppException{
        try {
            request.checkIsCancle();
            OkHttpURLConnection connection= (OkHttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15*3000);
            connection.setReadTimeout(15*3000);
            request.checkIsCancle();
            return connection;
        }catch (InterruptedIOException e){
            throw new AppException(AppException.ErrorType.TIMEOUT,e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.IO,e.getMessage());
        }
    }

    public static OkHttpURLConnection post(Request request) throws AppException{
        try {
            request.checkIsCancle();
            OkHttpURLConnection connection= (OkHttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15*3000);
            connection.setReadTimeout(15*3000);
            connection.setDoOutput(true);
            addHeader(connection,request.headers);

            OutputStream os=connection.getOutputStream();
            if (request.filePath!=null)
            {
                UploadUtil.upload(os,request.filePath);
            }else if(request.fileEntities!=null)
            {
                UploadUtil.upload(os,request.content,request.fileEntities);
            }else if (request.content!=null)
            {
                os.write(request.content.getBytes());
            }else
            {
                throw new AppException(AppException.ErrorType.MANUAL,"手动输入造成异常");
            }
            request.checkIsCancle();
            return connection;
        }catch (InterruptedIOException e){
            throw new AppException(AppException.ErrorType.TIMEOUT,e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.IO,e.getMessage());
        }
    }

    public static void addHeader(OkHttpURLConnection connection, Map <String,String> headers)
    {
        if (headers==null||headers.size()==0)
            return;
        for (Map.Entry<String,String> entry:headers.entrySet())
        {
            connection.addRequestProperty(entry.getKey(),entry.getValue());
        }
    }
}

package com.kevin.http;

import android.webkit.URLUtil;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUrlConnectionUtil {

    public static HttpURLConnection exec(Request request) throws AppException {

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

    public static HttpURLConnection get(Request request) throws AppException{
        try {
            HttpURLConnection connection= (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15*3000);
            connection.setReadTimeout(15*3000);
            return connection;
        }catch (InterruptedIOException e){
            throw new AppException(AppException.ErrorType.TIMEOUT,e.getMessage());
        } catch (IOException e) {
           throw new AppException(AppException.ErrorType.IO,e.getMessage());
        }
    }

    public static HttpURLConnection post(Request request) throws AppException{
        try {
            HttpURLConnection connection= (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15*3000);
            connection.setReadTimeout(15*3000);
            connection.setDoOutput(true);
            addHeader(connection,request.headers);

            OutputStream os=connection.getOutputStream();
            os.write(request.content.getBytes());

            return connection;
        }catch (InterruptedIOException e){
            throw new AppException(AppException.ErrorType.TIMEOUT,e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.IO,e.getMessage());
        }
    }

    public static void addHeader( HttpURLConnection connection,Map <String,String> headers)
    {
        if (headers==null||headers.size()==0)
            return;
        for (Map.Entry<String,String> entry:headers.entrySet())
        {
            connection.addRequestProperty(entry.getKey(),entry.getValue());
        }
    }
}

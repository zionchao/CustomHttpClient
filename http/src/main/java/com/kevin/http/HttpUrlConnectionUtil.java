package com.kevin.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUrlConnectionUtil {

    public static HttpURLConnection exec(Request request) throws IOException {

        switch (request.method)
        {
            case GET:
                return get(request);

            case POST:
                 return post(request);
        }
        return null;
    }

    public static HttpURLConnection get(Request request) throws IOException{
        HttpURLConnection connection= (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(15*3000);
        connection.setReadTimeout(15*3000);
         return connection;
    }

    public static HttpURLConnection post(Request request) throws IOException{
        HttpURLConnection connection= (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(15*3000);
        connection.setReadTimeout(15*3000);
        connection.setDoOutput(true);
        addHeader(connection,request.headers);

        OutputStream os=connection.getOutputStream();
        os.write(request.content.getBytes());

        return connection;
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

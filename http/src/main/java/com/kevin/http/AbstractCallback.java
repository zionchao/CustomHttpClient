package com.kevin.http;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by zhangchao_a on 2016/9/19.
 */
public abstract class AbstractCallback<T> implements ICallback<T> {

    private Class<T> entityClass;
    private String path;

    @Override
    public T parseResponse(HttpURLConnection connection) throws Exception{
        int status =connection.getResponseCode();
        if (status>=200&&status<400)
        {
            if (path==null)
            {
                ByteArrayOutputStream out=new ByteArrayOutputStream();
                InputStream is=connection.getInputStream();
                byte[]buffer=new byte[2048];
                int len;
                while ((len=is.read(buffer))!=-1)
                {
                    out.write(buffer,0,len);
                }
                is.close();
                out.flush();
                out.close();
                String result= new String(out.toByteArray());
                return bindData(result);
            }else
            {
                FileOutputStream out=new FileOutputStream(path);
                InputStream is=connection.getInputStream();
                byte[]buffer=new byte[2048];
                int len;
                while ((len=is.read(buffer))!=-1)
                {
                    out.write(buffer,0,len);
                }
                is.close();
                out.flush();
                out.close();
                return bindData(path);

            }

        }else
            throw new Exception();
    }

    protected abstract T bindData(String result);

    public ICallback setCachePath(String path) {
        this.path=path;
        return this;
    }
}

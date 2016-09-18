package com.example;

import java.util.Map;

/**
 * Created by zhangchao_a on 2016/9/18.
 */
public class Request {

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
        this.method=RequestMethod.GET;
    }

    public  Request(String url,RequestMethod method)
    {
        this.url=url;
        this.method=method;
    }

}

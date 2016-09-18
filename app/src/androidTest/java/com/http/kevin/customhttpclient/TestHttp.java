package com.http.kevin.customhttpclient;

import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by zhangchao_a on 2016/9/18.
 */
public class TestHttp extends InstrumentationTestCase {

    public void testHttpGet() throws Throwable
    {
        String url="http://api.stay4it.com";
        Request request=new Request();
        request.url=url;
        String result=HttpUrlConnectionUtil.exec(request);
        Log.e("stay","testHttpGet return"+result);
    }
}

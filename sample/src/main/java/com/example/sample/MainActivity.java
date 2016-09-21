package com.example.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kevin.http.AppException;
import com.kevin.http.FileCallback;
import com.kevin.http.JsonCallback;
import com.kevin.http.Request;
import com.kevin.http.RequestTask;

import java.util.HashMap;

public class  MainActivity extends BaseActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult= (TextView) findViewById(R.id.result);
    }

    public void clickMe(View view)
    {
        tvResult.setText("");
        String url="http://117.185.122.131:8380/admin/spi/getTasks.spi";
        String content="{\"sn\":\"005801FF0031082003EA08A5C80B4B11\",\"taskCodes\":[]}";
        Request request=new Request(url, Request.RequestMethod.POST);
        request.headers=new HashMap<String, String>();
        request.headers.put("Content-Type","application/json");
        request.setCallback(new JsonCallback<ResultGetTask>() {
            @Override
            public void onSuccess(ResultGetTask result) {
                tvResult.setText("成功---"+result.toString());
                Log.e("hehe",result.toString());
            }

            @Override
            public void onFailuer(AppException error) {
                tvResult.setText("失败---"+error.getMessage().toString());
                Log.e("hehe",error.getMessage());
            }
        }.setCachePath("/sdcard/demo.txt"));
        request.content=content;
        request.setOnGloableListener(this);
        RequestTask task=new RequestTask(request);
        task.execute();
    }

    public void clickFile(View view)
    {
        tvResult.setText("");
        String url="http://117.169.64.125:8380/admin/spi/getTasks.spi"; //jx
//      String content="{\"sn\":\"005801FF0031082003EA08A5C80B4B11\",\"taskCodes\":[]}";
        String content="{\"sn\":\"0CC655CED6D3\",\"taskCodes\":[]}";

        Request request=new Request(url, Request.RequestMethod.POST);
        request.headers=new HashMap<String, String>();
        request.headers.put("Content-Type","application/json");
        request.setCallback(new FileCallback() {
            @Override
            public void onProgressUpdata(int curLen, int totalLen) {
                Log.e("hehe",curLen+"："+totalLen);
            }

            @Override
            public void onSuccess(String result) {
                tvResult.setText("成功---"+result.toString());
                Log.e("hehe",result.toString());
            }

            @Override
            public void onFailuer(AppException error) {
                tvResult.setText("失败---"+error.getMessage().toString());
                Log.e("hehe",error.getMessage());
            }
        }.setCachePath("/sdcard/demo.txt"));
        request.content=content;
        request.enableProgressUpdate(true);
        RequestTask task=new RequestTask(request);
        task.execute();
    }
}

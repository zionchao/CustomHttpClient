package com.example.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kevin.http.ICallback;
import com.kevin.http.Request;
import com.kevin.http.RequestTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMe(View view)
    {
        String url="http://api.stay4it.com/";
        Request request=new Request(url);
        request.setCallback(new ICallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("hehe",result);
            }

            @Override
            public void onFailuer(Exception error) {
                Log.e("hehe",error.getMessage());
            }
        });
        RequestTask task=new RequestTask(request);
        task.execute();
    }

}

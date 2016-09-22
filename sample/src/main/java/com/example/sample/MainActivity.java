package com.example.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.http.AppException;
import com.kevin.http.FileCallback;
import com.kevin.http.JsonCallback;
import com.kevin.http.Request;
import com.kevin.http.RequestManager;
import com.kevin.http.RequestTask;

import java.util.HashMap;

import static com.example.sample.R.id.result;

public class  MainActivity extends BaseActivity {

    private TextView tvResult;
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult= (TextView) findViewById(result);
        click= (Button) findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                clickDownload();
                clickMe();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        RequestManager.getInstance().cancleRequest(toString());
    }

    private void clickDownload() {

        tvResult.setText("");
        String url="http://img3.imgtn.bdimg.com/it/u=2571219862,1253096831&fm=11&gp=0.jpg"; //jx
//      String content="{\"sn\":\"005801FF0031082003EA08A5C80B4B11\",\"taskCodes\":[]}";
//        String content="{\"sn\":\"0CC655CED6D3\",\"taskCodes\":[]}";

        final Request request=new Request(url);
        request.headers=new HashMap<String, String>();
//        request.headers.put("Content-Type","application/json");
        request.setCallback(new FileCallback() {
            @Override
            public void onProgressUpdata(int curLen, int totalLen) {
                Log.e("hehe",curLen+"："+totalLen);
                if(curLen*100L/totalLen>50)
                {
                    request.cancle(true);
                }
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
        request.enableProgressUpdate(true);
        RequestTask task=new RequestTask(request);
        task.execute();
    }

    public void clickMe()
    {
        tvResult.setText("");
        String url="http://117.185.122.131:8380/admin/spi/getTasks.spi";
        String content="{\"sn\":\"005801FF0031082003EA08A5C80B4B11\",\"taskCodes\":[]}";
        Request request=new Request(url, Request.RequestMethod.POST);
        request.headers=new HashMap<>();
        request.headers.put("Content-Type","application/json");
        request.setCallback(new JsonCallback<ResultGetTask>() {

            @Override
            public ResultGetTask onPreRequest() {
                // TODO fetch data from db
                return super.onPreRequest();
            }

            @Override
            public void refreshUI() {
                Toast.makeText(MainActivity.this,"hehe",1).show();
            }

            @Override
            public ResultGetTask onPostRequest(ResultGetTask resultGetTask) {
                Log.e("hehe",resultGetTask.getData().get(0).getScenesName());
                return resultGetTask;
            }

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
        });
        request.content=content;
        request.setOnGloableListener(this);
        RequestManager.getInstance().performRequest(request);
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
        request.setTag(toString());
        RequestManager.getInstance().performRequest(request);
    }
}

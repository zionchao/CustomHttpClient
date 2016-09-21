package com.example.sample;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.kevin.http.AppException;
import com.kevin.http.OnGloableExceptionListener;

/**
 * Created by ZhangChao on 2016/9/20.
 */

public class BaseActivity extends AppCompatActivity implements OnGloableExceptionListener {
    @Override
    public boolean handleException(AppException e) {
        if (e.status==403)
        {
            if(e.responseMessage.equals("need relog in"))
            {
                AlertDialog dialog=new AlertDialog.Builder(this).setMessage(e.responseMessage)
                        .setTitle("haha")
                        .show();
                return true;
            }
        }
        return false;
    }
}

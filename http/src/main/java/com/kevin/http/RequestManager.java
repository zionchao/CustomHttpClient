package com.kevin.http;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangChao on 2016/9/21.
 */

public class RequestManager {

    private HashMap<String,ArrayList<Request>> reqSet;
    private static RequestManager manager;
    private RequestManager()
    {
        reqSet=new HashMap<>();
    }
    public static RequestManager getInstance() {
        if (manager==null)
        {
            manager=new RequestManager();
        }
        return manager;
    }

    public void performRequest(Request request)
    {
        RequestTask task=new RequestTask(request);
        task.execute();
        if (!reqSet.containsKey(request.tag))
        {
            ArrayList<Request> reqList=new ArrayList<>();
            reqList.add(request);
            reqSet.put(request.tag,reqList);
        }else
          reqSet.get(request.tag).add(request);
    }

    public void cancleRequest(String tag)
    {
        if(tag==null||tag.equals(""))
            return;
        if (reqSet.containsKey(tag))
        {
            ArrayList<Request> reqList=reqSet.remove(tag);
            for (Request req:reqList)
                req.cancle(true);
        }

    }

    public void cancleAll()
    {
       for (Map.Entry<String,ArrayList<Request>> map:reqSet.entrySet()){
           ArrayList<Request> reqList=reqSet.get(map.getKey());
               for (Request req:reqList)
                   if (!req.isCancleHttp)
                        req.cancle(true);
       }
    }
}

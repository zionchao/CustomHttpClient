package com.kevin.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

/**
 * Created by zhangchao_a on 2016/9/19.
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {
    @Override
    protected T bindData(String result) {
        Gson gson=new Gson();
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        Class<T> entityClass = (Class<T>) (parameterizedType.getActualTypeArguments()[0]);
        T t=gson.fromJson(result,entityClass);
        return t;
    }
}

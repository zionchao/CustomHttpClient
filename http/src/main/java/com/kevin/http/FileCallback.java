package com.kevin.http;

/**
 * Created by zhangchao_a on 2016/9/19.
 */
public abstract class FileCallback extends AbstractCallback<String> {
    @Override
    protected String bindData(String path) {
        return path;
    }
}

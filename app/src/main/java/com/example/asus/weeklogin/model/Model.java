package com.example.asus.weeklogin.model;

import com.example.asus.weeklogin.inter.ICallBack;
import com.example.asus.weeklogin.utils.HttpUtils;

import java.lang.reflect.Type;

public class Model {
    public void login(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);

    }
}

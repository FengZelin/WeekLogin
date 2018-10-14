package com.example.asus.weeklogin.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.asus.weeklogin.bean.Bean;
import com.example.asus.weeklogin.bean.PagerBean;
import com.example.asus.weeklogin.bean.RegisterBean;
import com.example.asus.weeklogin.inter.ICallBack;
import com.example.asus.weeklogin.model.Model;
import com.example.asus.weeklogin.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Presenter {
    private IView iv;
    private Model model;

    public void attach(IView iv) {
        this.iv = iv;
        model = new Model();
    }

    public void check() {
        if (TextUtils.isEmpty(iv.getUsername()) || TextUtils.isEmpty(iv.getPassword())) {
            iv.check(false);
        } else {
            iv.check(true);
        }
    }

    public void Banner(String url) {

        final Type type = new TypeToken<PagerBean>() {
        }.getType();
        model.login(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                PagerBean banner = (PagerBean) obj;
                if (banner != null) {
                    List<PagerBean.DataBean> data = banner.getData();
                    Log.i("12432545", "success: "+data.size());
                    iv.success(data);
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        }, type);
    }
    public void Add(String url) {
        String username = iv.getUsername();
        String password = iv.getPassword();
        url = url + "?mobile=" + username + "&password=" + password;
        Type type = new TypeToken<RegisterBean>() {
        }.getType();
        model.login(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iv.success(obj);
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        }, type);
    }


    public void request(String url) {
        final String username = iv.getUsername();
        final String password = iv.getPassword();
        url = url + "?mobile=" + username + "&password=" + password;
        final Type type = new TypeToken<Bean>() {
        }.getType();
        model.login(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iv.success(obj);
                SharedPreferences sp = iv.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                sp.edit().putString("username", username)
                        .commit();
                Bean ben = (Bean) obj;
                if (ben.getCode().equals("0")) {
                    iv.Goto();
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        }, type);
    }
}


package com.example.asus.weeklogin.view;

import android.content.Context;

public interface IView<T> {
    void success(T t);

    void failed(Exception e);

    String getUsername();

    String getPassword();

    void check(boolean isChecked);
    void Goto();
    Context getContext();

}

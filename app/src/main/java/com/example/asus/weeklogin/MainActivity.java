package com.example.asus.weeklogin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.weeklogin.bean.Bean;
import com.example.asus.weeklogin.presenter.Presenter;
import com.example.asus.weeklogin.view.IView;

public class MainActivity extends AppCompatActivity implements IView<Bean>, View.OnClickListener {
    private EditText dqusername,dqpassword;
    private Button but_login,but_register;
    private Presenter presenter;
    private String url="https://www.zhaoapi.cn/user/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        获取资源ID
        dqusername=findViewById(R.id.name);
        dqpassword=findViewById(R.id.password);
        but_login=findViewById(R.id.login);
        but_register=findViewById(R.id.register);
//        创建一个方法名
        presenter = new Presenter();
        presenter.attach(this);

        but_login.setOnClickListener(this);

        but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,RegisterAcyivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                presenter.check();
                break;
            case R.id.register:
                Intent intent = new Intent(this, RegisterAcyivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void success(Bean bean) {
        if(bean!=null){
            Toast.makeText(this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"网络异常"+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUsername() {
        return dqusername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return dqpassword.getText().toString().trim();
    }

    @Override
    public void check(boolean isChecked) {
        if(isChecked){
            presenter.request(url);
        }else{
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Goto() {
        Intent intent =new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}

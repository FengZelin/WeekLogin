package com.example.asus.weeklogin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.weeklogin.bean.RegisterBean;
import com.example.asus.weeklogin.presenter.Presenter;
import com.example.asus.weeklogin.view.IView;

public class RegisterAcyivity extends AppCompatActivity implements IView<RegisterBean>, View.OnClickListener{
    private Button registerl;
    private Presenter presenter;
    private ProgressDialog pd;
    private EditText newname, newpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter();
        presenter.attach(this);

        pd = new ProgressDialog(this);
        pd.setMessage("正在注册，请稍候...");


        setContentView(R.layout.activity_register_acyivity);
        registerl = findViewById(R.id.registerlogin);
        newname = findViewById(R.id.newname);
        newpassword = findViewById(R.id.newpassword);
        registerl.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerlogin:
                presenter.check();
                break;
        }
    }

    @Override
    public void success(RegisterBean registerBean) {
        if(registerBean!=null){
            Toast.makeText(this,registerBean.getMsg(),Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"网络异常"+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUsername() {
        return newname.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return newpassword.getText().toString().trim();
    }

    @Override
    public void check(boolean isChecked) {
        if(isChecked){
            presenter.Add("https://www.zhaoapi.cn/user/reg");
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



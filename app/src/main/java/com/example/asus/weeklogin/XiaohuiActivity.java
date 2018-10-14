package com.example.asus.weeklogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class XiaohuiActivity extends AppCompatActivity {
    private TextView txtUsername;
    private TextView txtNicheng;
    private Button btnGotoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaohui);
        txtUsername=findViewById(R.id.username);
        txtNicheng=findViewById(R.id.nicheng);
        btnGotoLogin=findViewById(R.id.btn_gotoLogin);
//        数据储存
        SharedPreferences sp=getSharedPreferences("config",MODE_PRIVATE);
        String username = sp.getString("username", "");
        txtUsername.setText(username);
        txtNicheng.setText(username);

        btnGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
                config.edit().clear().commit();
                Intent intent=new Intent(XiaohuiActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

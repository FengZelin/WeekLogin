package com.example.asus.weeklogin;

import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.asus.weeklogin.fragment.Fragmentone;
import com.example.asus.weeklogin.fragment.Fragmenttow;

public class LoginActivity extends FragmentActivity {
    private RadioGroup radio_group;
    private Fragmentone fragmentone;
    private Fragmenttow fragmenttow;
    private android.support.v4.app.FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        radio_group = findViewById(R.id.radio_group);
        //创建Fragment对象
        fragmentone = new Fragmentone();
        fragmenttow = new Fragmenttow();

        manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.fram_layout,fragmentone).commit();

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                        transaction.replace(R.id.fram_layout,fragmentone);
                        break;
                    case R.id.rb2:
                        transaction.replace(R.id.fram_layout,fragmenttow);
                        break;
                    default:
                        break;
                }
                transaction.commit();
            }
        });

    }
    }


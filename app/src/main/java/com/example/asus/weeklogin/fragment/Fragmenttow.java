package com.example.asus.weeklogin.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.weeklogin.R;
import com.example.asus.weeklogin.XiaohuiActivity;

public class Fragmenttow extends Fragment implements View.OnClickListener {
    private ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragmenttow,container,false);
        imageView=view.findViewById(R.id.imageview);
        imageView.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        Intent it=new Intent(getActivity(),XiaohuiActivity.class);
        startActivity(it);
    }
}

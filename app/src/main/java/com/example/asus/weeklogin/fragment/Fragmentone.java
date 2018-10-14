package com.example.asus.weeklogin.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.weeklogin.R;
import com.example.asus.weeklogin.adpater.BanerPager;
import com.example.asus.weeklogin.bean.PagerBean;
import com.example.asus.weeklogin.presenter.Presenter;
import com.example.asus.weeklogin.view.IView;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Fragmentone extends Fragment implements IView<List<PagerBean.DataBean>> {
    private ViewPager vpBanner;
    private TextView tv_content;
    private EditText et_input;
    private ArrayList<PagerBean> list;
    private ImageView img;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                int current = vpBanner.getCurrentItem();
                vpBanner.setCurrentItem(current+1);
                sendEmptyMessageDelayed(0, 2000);
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragmentone, container, false);
        vpBanner=view.findViewById(R.id.vpBanner);
        tv_content = view.findViewById(R.id.tv_content);
        et_input = view.findViewById(R.id.et_input);
        img = view.findViewById(R.id.img);
        list = new ArrayList<>();
        Presenter presenter = new Presenter();
        presenter.attach(this);

        presenter.Banner("https://www.zhaoapi.cn/ad/getAd");
        view.findViewById(R.id.btnSan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 0);
            }
        });

        view.findViewById(R.id.btn_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et_input.getText().toString();
                if (str.equals("")) {
                    Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // 位图
                    try {
                        /**
                         * 参数：1.文本 2 3.二维码的宽高 4.二维码中间的那个logo
                         */
                        Bitmap bitmap = EncodingUtils.createQRCode(str, 500, 500, null);
                        // 设置图片
                        img.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            tv_content.setText(result);
        }
    }

    @Override
    public void success(final List<PagerBean.DataBean> list) {
        // TODO: 2018/10/14  轮播图
        BanerPager adapter = new BanerPager(getActivity(),list);
        vpBanner.setAdapter(adapter);
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void check(boolean isChecked) {

    }

    @Override
    public void Goto() {

    }
}

package com.example.asus.weeklogin.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.asus.weeklogin.bean.PagerBean;

import java.util.List;

public class BanerPager extends PagerAdapter {

    private Context context;
    private List<PagerBean.DataBean> data;

    public BanerPager(Context context, List<PagerBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(imageView.getScaleType().FIT_XY);

        PagerBean.DataBean dataBean1 = data.get(position % data.size());

        String icon = dataBean1.getIcon();
        Glide.with(context).load(icon).into(imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //一定要删除 super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}


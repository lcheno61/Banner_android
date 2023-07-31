package com.example.banner_android.UI.Banner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.example.banner_android.R;
import com.example.banner_android.Utils;

import java.util.List;

public class Banner extends RelativeLayout {

        protected Utils utils = Utils.getInstance();
    private ViewPager viewPager;
    private RadioGroup pageControl;
    private BannerAdapter bannerAdapter;
    private int currentIndex;
    private int dataCount;

    public Banner(Context context) {
        super(context);
    }
    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_banner, this, true);
        viewPager = (ViewPager) view.findViewById(R.id.vp_banner);
        pageControl = (RadioGroup) view.findViewById(R.id.pageControl);
        currentIndex = 0;
        bannerAdapter = new BannerAdapter();
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 0) {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem == 0) {
                    currentIndex = (currentIndex - 1 + dataCount) % dataCount;
                } else if (currentItem == 2) {
                    currentIndex = (currentIndex + 1) % dataCount;
                }
                if (currentItem != 1) {
                    for (int i = 0; i < pageControl.getChildCount(); i++) {
                        ((RadioButton) pageControl.getChildAt(i)).setSelected(i == currentIndex);
                    }
                    bannerAdapter.reloadImage(currentIndex);
                    viewPager.setCurrentItem(1 , false);
                }
            }
        }
    };
    public void setBannerArray(List<BannerModel> bannerData) {
        bannerAdapter.setBannerArray(bannerData);
        dataCount = bannerData.size();
        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.view_banner_item, viewPager, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.banner_item);
            int index = (i == 0) ? ((i + dataCount - 1) % dataCount) : (i - 1);
            Bitmap bitmap = bannerData.get(index).getBitmap();
            if (bitmap != null) { imageView.setImageBitmap(bitmap); }
            int resId = bannerData.get(index).getResourceId();
            if (resId != 0) { imageView.setImageResource(resId); }

            if (i == 1) {
                imageView.setOnClickListener(v -> {
                    String url = bannerAdapter.getUrl(currentIndex);
                    if (!url.isEmpty()) {
                        utils.openUrl(url);
                    }
                });
            }
            bannerAdapter.addView(view);
        }
        for (int i = 0; i < dataCount; i++) {
            View indicator = LayoutInflater.from(getContext()).inflate(R.layout.view_indicator_item, viewPager, false);
            RadioButton radioButton = (RadioButton) indicator.findViewById(R.id.indicator_item);
            if (i == 0) { radioButton.setSelected(true); }
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(dip2px(getContext(), 10f), dip2px(getContext(), 10f));
            layoutParams.leftMargin = dip2px(getContext(), 10);
            pageControl.addView(radioButton, layoutParams);
        }
        viewPager.setAdapter(bannerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setCurrentItem(1);
    }
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public void switchPage(final long delayMillis) {
        Handler mHandler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(2, true);
                mHandler.postDelayed(this, delayMillis);
            }
        };
        mHandler.postDelayed(r, delayMillis);
    }
}


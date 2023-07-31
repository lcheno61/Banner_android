package com.example.banner_android.UI.Banner;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.banner_android.R;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private List<View> views = new ArrayList<>();
    private List<BannerModel> bannerArray;

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position % views.size()));
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position % views.size());
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    public void setBannerArray(List<BannerModel> bannerData) {
        bannerArray = bannerData;
    }
    public void addView(View view) {
        views.add(view);
    }

    public String getUrl(int index) {
        String url = bannerArray.get(index).getUrl();
        return url;
    }
    public void reloadImage(int index) {
        int count = bannerArray.size();
        if (count != 0 && views.size() == 3) {
            int lastOne = (index - 1 + count) % count;
            int nowIndex = index % count;
            int nextOne = (index + 1) % count;
            ImageView lastImageView = (ImageView) views.get(0).findViewById(R.id.banner_item);
            ImageView nowImageView = (ImageView) views.get(1).findViewById(R.id.banner_item);
            ImageView nextImageView = (ImageView) views.get(2).findViewById(R.id.banner_item);

            Bitmap bitmap = bannerArray.get(lastOne).getBitmap();
            if (bitmap != null) { lastImageView.setImageBitmap(bitmap); }
            int resId = bannerArray.get(lastOne).getResourceId();
            if (resId != 0) { lastImageView.setImageResource(resId); }

            bitmap = bannerArray.get(nowIndex).getBitmap();
            if (bitmap != null) { nowImageView.setImageBitmap(bitmap); }
            resId = bannerArray.get(nowIndex).getResourceId();
            if (resId != 0) { nowImageView.setImageResource(resId); }

            bitmap = bannerArray.get(nextOne).getBitmap();
            if (bitmap != null) { nextImageView.setImageBitmap(bitmap); }
            resId = bannerArray.get(nextOne).getResourceId();
            if (resId != 0) { nextImageView.setImageResource(resId); }

        }

    }
}

package com.example.banner_android.UI.Banner;

import android.graphics.Bitmap;

public class BannerModel {
    private int mResourceId;
    private String mUrl;
    private Bitmap mBitmap;

    public void setResourceId(int resId) { mResourceId = resId; }
    public int getResourceId() { return mResourceId; }
    public void setUrl(String url) { mUrl = url; }
    public String getUrl() { return mUrl; }
    public void setBitmap(Bitmap bitmap) { mBitmap = bitmap; }
    public Bitmap getBitmap() { return mBitmap; }
}

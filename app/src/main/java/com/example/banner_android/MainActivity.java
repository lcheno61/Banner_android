package com.example.banner_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.banner_android.UI.Banner.Banner;
import com.example.banner_android.UI.Banner.BannerModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.activity = this;
        Banner banner = (Banner) findViewById(R.id.b_banner);
        int[] imageList = new int[]{R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4,};
        String[] urlList = new String[]{"www.google.com.",
                "chat.openai.com",
                "www.apple.com",
                "www.android.com",};

        List<BannerModel> bannerModel = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BannerModel model = new BannerModel();
            model.setResourceId(imageList[i]);
            String url = urlList[i];
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
            model.setUrl(url);
            bannerModel.add(model);
        }
        banner.setBannerArray(bannerModel);
        banner.switchPage(3000);
    }
}
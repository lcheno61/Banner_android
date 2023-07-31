package com.example.banner_android;

import android.content.Intent;
import android.net.Uri;

import java.net.URLDecoder;

public class Utils {

    private static Utils instance = null;

    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }
    public void openUrl(String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            App.activity.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

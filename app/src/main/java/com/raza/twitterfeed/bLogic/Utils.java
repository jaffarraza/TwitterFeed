package com.raza.twitterfeed.bLogic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by jaffarraza on 03/08/16.
 */
public class Utils {
    static Utils utils = new Utils();

    public static Utils getInstance() {
        return utils;
    }

    public void setLocale(Context ctx, String lang) {
        Resources res = ctx.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang);
        res.updateConfiguration(conf, dm);
        Intent intent = ((Activity) ctx).getIntent();
        ((Activity) ctx).finish();
        ((Activity) ctx).startActivity(intent);
    }
}

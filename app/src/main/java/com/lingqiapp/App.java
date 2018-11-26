package com.lingqiapp;

import android.content.Context;
import android.os.Build;
import android.os.LocaleList;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hss01248.frescopicker.FrescoIniter;
import com.lingqiapp.Utils.PausableThreadPoolExecutor;
import com.mob.MobSDK;
import com.tencent.bugly.Bugly;
import com.tencent.smtt.sdk.QbSdk;

import java.util.Locale;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import me.iwf.photopicker.PhotoPickUtils;

/**
 * Created by 赵磊 on 2017/7/12.
 */

public class App extends MultiDexApplication {
    /**
     * 先创建一个请求队列，因为这个队列是全局的，所以在Application中声明这个队列
     */
    public static RequestQueue queues;
    public static PausableThreadPoolExecutor pausableThreadPoolExecutor;

    public static String LanguageTYPE = "1";


    public static Context context;

    protected String getAppkey() {
        return null;
    }

    protected String getAppSecret() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        MobSDK.init(this);
        QbSdk.initX5Environment(this, null);
        MultiDex.install(this);
        Bugly.init(getApplicationContext(), "b9e3fb47cb", false);
        queues = Volley.newRequestQueue(getApplicationContext());
        Fresco.initialize(this);
        pausableThreadPoolExecutor = new PausableThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        PhotoPickUtils.init(getApplicationContext(), new FrescoIniter());//第二个参数根据具体依赖库而定
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }

        String language = locale.getLanguage() + "-" + locale.getCountry();

//        if (locale.getLanguage().equals("pt")) {
//            LanguageTYPE = "1";
//        } else if (locale.getLanguage().equals("es")) {
//            LanguageTYPE = "2";
//        } else if (locale.getLanguage().equals("en")) {
//            LanguageTYPE = "3";
//        } else if (locale.getLanguage().equals("it")) {
//            LanguageTYPE = "4";
//        } else if (locale.getLanguage().equals("fr")) {
//            LanguageTYPE = "5";
//        }

        Log.e("App", language);
    }

    public static RequestQueue getQueues() {
        return queues;
    }
}

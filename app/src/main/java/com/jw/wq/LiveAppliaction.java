package com.jw.wq;

import android.app.Application;

/**
 * Created by Sight-WXC on 2016/7/4 0004.
 */
public class LiveAppliaction extends Application {


    private LiveAppliaction appliaction;


    @Override
    public void onCreate() {
        super.onCreate();

        appliaction =this;

    }
}

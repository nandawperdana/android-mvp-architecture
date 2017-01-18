package com.nandawperdana.androidmvp.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // leak canary install
        LeakCanary.install(this);
    }
}

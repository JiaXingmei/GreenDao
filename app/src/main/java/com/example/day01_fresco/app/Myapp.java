package com.example.day01_fresco.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}

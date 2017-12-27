package com.toandoan.todoapp;

import android.app.Application;

import com.toandoan.todoapp.di.DaggerAppComponent;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public class ReposApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().build();
    }
}

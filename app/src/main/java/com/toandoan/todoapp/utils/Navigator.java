package com.toandoan.todoapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by doan.van.toan on 12/27/17.
 */

public class Navigator {
    private Activity mActivity;

    public Navigator(Activity activity) {
        mActivity = activity;
    }

    public void startActivity(Intent intent){
        mActivity.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int requestCode){
        mActivity.startActivityForResult(intent, requestCode);
    }

    public Context getContext(){
        return mActivity;
    }
}

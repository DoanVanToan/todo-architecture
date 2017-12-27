package com.toandoan.todoapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public abstract class AndroidBaseViewModel extends AndroidViewModel{
    public AndroidBaseViewModel(@NonNull Application application) {
        super(application);
    }

    protected abstract void onCreate();

    protected abstract void onStop();

}

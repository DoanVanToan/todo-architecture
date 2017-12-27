package com.toandoan.todoapp.screen.detail;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.toandoan.todoapp.AndroidBaseViewModel;
import com.toandoan.todoapp.data.model.Repo;

/**
 * Created by doan.van.toan on 12/27/17.
 */

public class DetailViewModel extends AndroidBaseViewModel {
    public ObservableField<Repo> repo = new ObservableField<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void setRepo(Repo _repo){
        repo.set(_repo);
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onStop() {

    }
}

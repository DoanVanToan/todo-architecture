package com.toandoan.todoapp.screen.detail;

import android.arch.lifecycle.ViewModelProviders;

import com.toandoan.todoapp.data.model.Repo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doan.van.toan on 12/27/17.
 */
@Module
public class DetailModule {
    private DetailActivity mActivity;
    private Repo mRepo;

    public DetailModule(DetailActivity activity, Repo repo) {
        mActivity = activity;
        mRepo = repo;
    }

    @Provides
    DetailViewModel providesDetailViewModel(){
        DetailViewModel viewModel = ViewModelProviders.of(mActivity).get(DetailViewModel.class);
        viewModel.setRepo(mRepo);
        return viewModel;
    }
}

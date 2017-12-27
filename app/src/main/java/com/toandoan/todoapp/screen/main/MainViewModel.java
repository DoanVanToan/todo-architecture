package com.toandoan.todoapp.screen.main;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.toandoan.todoapp.AndroidBaseViewModel;
import com.toandoan.todoapp.data.model.ApiResponse;
import com.toandoan.todoapp.data.model.Repo;
import com.toandoan.todoapp.data.source.ReposRepository;
import com.toandoan.todoapp.screen.detail.DetailActivity;
import com.toandoan.todoapp.utils.EndlessScrollListener;
import com.toandoan.todoapp.utils.Navigator;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public class MainViewModel extends AndroidBaseViewModel implements RepoItemClickListener {
    private static final String USER_NAME = "doanvantoan";

    public ObservableField<MainAdapter> mAdapter;
    public ObservableField<EndlessScrollListener> mScrollListener = new ObservableField<>();

    private int mPage = 1;

    private ReposRepository mRepository;
    private LifecycleOwner mLifecycleOwner;
    private Navigator mNavigator;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mAdapter = new ObservableField<>(new MainAdapter());
        mAdapter.get().setRepoItemClickListener(this);
        mScrollListener.set(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                getData(USER_NAME, ++mPage);
            }
        });
    }

    private void getData(String userName, int page) {
        mRepository.getUserProject(userName, page)
                .observe(mLifecycleOwner, new Observer<ApiResponse<List<Repo>>>() {
                    @Override
                    public void onChanged(@Nullable ApiResponse<List<Repo>> listApiResponse) {
                        mAdapter.get().addData(listApiResponse.getBody());
                        mScrollListener.get().resetState();
                    }
                });
    }

    @Override
    public void onItemRepoClicked(Repo repo) {
        mNavigator.startActivity(DetailActivity.getInstance(mNavigator.getContext(), repo));
    }

    public void setRepository(ReposRepository repository) {
        mRepository = repository;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    public void setNavigator(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    protected void onCreate() {
        getData(USER_NAME, mPage);
    }

    @Override
    protected void onStop() {

    }
}

package com.toandoan.todoapp.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.toandoan.todoapp.BaseActivity;
import com.toandoan.todoapp.R;
import com.toandoan.todoapp.data.model.Repo;
import com.toandoan.todoapp.databinding.ActivityDetailBinding;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity {
    public static final String EXTRA_REPO = "EXTRA_REPO";

    @Inject
    DetailViewModel mViewModel;

    public static Intent getInstance(Context context, Repo repo){
        return new Intent(context, DetailActivity.class)
                .putExtra(EXTRA_REPO, repo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repo repo = getIntent().getExtras().getParcelable(EXTRA_REPO);
        DaggerDetailComponent.builder()
                .detailModule(new DetailModule(this, repo))
                .build()
                .inject(this);

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setViewModel(mViewModel);
        mViewModel.onCreate();
        setSupportActionBar(binding.toolbar);
        setUpActionBar();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}

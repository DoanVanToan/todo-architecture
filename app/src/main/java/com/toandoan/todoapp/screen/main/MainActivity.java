package com.toandoan.todoapp.screen.main;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.toandoan.todoapp.R;
import com.toandoan.todoapp.databinding.ActivityMainBinding;
import com.toandoan.todoapp.di.AppModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    @Inject
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMainComponent.builder()
                .mainModule(new MainModule(this, this))
                .appModule(new AppModule())
                .build()
                .inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mViewModel);
        mViewModel.onCreate();

        getSupportActionBar().setTitle(R.string.title_main_screen);
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}

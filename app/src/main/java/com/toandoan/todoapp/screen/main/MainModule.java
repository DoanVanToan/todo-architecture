package com.toandoan.todoapp.screen.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;

import com.toandoan.todoapp.data.source.ReposDataSource;
import com.toandoan.todoapp.data.source.ReposRepository;
import com.toandoan.todoapp.data.source.remote.ReposRemoteDataSource;
import com.toandoan.todoapp.data.source.remote.api.ReposApi;
import com.toandoan.todoapp.di.AppModule;
import com.toandoan.todoapp.di.UserScope;
import com.toandoan.todoapp.utils.Navigator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doan.van.toan on 12/26/17.
 */
@Module(includes = AppModule.class)
@UserScope
public class MainModule {
    private MainActivity mActivity;
    private LifecycleOwner mLifecycleOwner;

    MainModule(MainActivity activity, LifecycleOwner lifecycleOwner) {
        mActivity = activity;
        mLifecycleOwner = lifecycleOwner;
    }

    @Provides
    MainViewModel providesMainViewModel(@Named("lifecycleOwner") LifecycleOwner lifecycleOwner,
                                        @Named("repository") ReposRepository reposRepository,
                                        @Named("navigator") Navigator navigator) {
        MainViewModel viewModel = ViewModelProviders.of(mActivity).get(MainViewModel.class);
        viewModel.setLifecycleOwner(lifecycleOwner);
        viewModel.setRepository(reposRepository);
        viewModel.setNavigator(navigator);
        return viewModel;
    }

    @Provides
    @Named("navigator")
    Navigator providesNavigator(){
        return new Navigator(mActivity);
    }

    @Provides
    @Named("lifecycleOwner")
    LifecycleOwner providesLifecyclerOwner() {
        return mLifecycleOwner;
    }

    @Provides
    @Named("repository")
    ReposRepository providesRepository(@Named("remoteDataSource")
                                               ReposDataSource remoteDataSource) {
        return new ReposRepository(remoteDataSource);
    }

    @Provides
    @Named("remoteDataSource")
    ReposDataSource providesRemoteDataSource(ReposApi reposApi) {
        return new ReposRemoteDataSource(reposApi);
    }
}

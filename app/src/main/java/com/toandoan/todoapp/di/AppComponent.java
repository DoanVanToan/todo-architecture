package com.toandoan.todoapp.di;

import com.toandoan.todoapp.data.source.remote.api.ReposApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by doan.van.toan on 12/26/17.
 */
@UserScope
@Component(modules = AppModule.class)
public interface AppComponent {
}

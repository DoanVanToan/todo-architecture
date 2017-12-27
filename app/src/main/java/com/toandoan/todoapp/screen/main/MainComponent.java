package com.toandoan.todoapp.screen.main;

import com.toandoan.todoapp.data.source.ReposRepository;
import com.toandoan.todoapp.di.UserScope;

import dagger.Component;

/**
 * Created by doan.van.toan on 12/26/17.
 */
@Component(modules = {MainModule.class})
@UserScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
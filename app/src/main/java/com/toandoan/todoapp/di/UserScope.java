package com.toandoan.todoapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Provides;

/**
 * Created by doan.van.toan on 12/26/17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}

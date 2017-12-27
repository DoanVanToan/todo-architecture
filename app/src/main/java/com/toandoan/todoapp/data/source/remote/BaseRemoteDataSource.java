package com.toandoan.todoapp.data.source.remote;

import com.toandoan.todoapp.data.source.remote.api.ReposApi;

import javax.inject.Inject;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public abstract class BaseRemoteDataSource {
    ReposApi mApi;

    protected BaseRemoteDataSource(ReposApi api) {
        mApi = api;
    }
}

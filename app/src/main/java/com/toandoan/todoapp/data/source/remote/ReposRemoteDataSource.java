package com.toandoan.todoapp.data.source.remote;

import android.arch.lifecycle.LiveData;

import com.toandoan.todoapp.data.model.ApiResponse;
import com.toandoan.todoapp.data.model.Repo;
import com.toandoan.todoapp.data.source.ReposDataSource;
import com.toandoan.todoapp.data.source.remote.api.ReposApi;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public class ReposRemoteDataSource extends BaseRemoteDataSource implements ReposDataSource {
    public final static String TYPE_ALL = "all";
    public final static String SORT_BY = "created";
    public final static String DIRECTION_BY = "desc";

    @Inject
    public ReposRemoteDataSource(ReposApi api) {
        super(api);
    }

    @Override
    public LiveData<ApiResponse<List<Repo>>> getUserProject(String user, int page) {
        return mApi.getUserProject(user, TYPE_ALL, SORT_BY, DIRECTION_BY, page);
    }
}

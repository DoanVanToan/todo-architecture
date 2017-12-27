package com.toandoan.todoapp.data.source;

import android.arch.lifecycle.LiveData;

import com.toandoan.todoapp.data.model.ApiResponse;
import com.toandoan.todoapp.data.model.Repo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public class ReposRepository implements ReposDataSource {
    private ReposDataSource mRemoteDataSource;

    @Inject
    public ReposRepository(ReposDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public LiveData<ApiResponse<List<Repo>>> getUserProject(String user, int page) {
        return mRemoteDataSource.getUserProject(user, page);
    }
}

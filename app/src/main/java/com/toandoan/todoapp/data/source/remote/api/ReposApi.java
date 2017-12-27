package com.toandoan.todoapp.data.source.remote.api;

import android.arch.lifecycle.LiveData;

import com.toandoan.todoapp.data.model.ApiResponse;
import com.toandoan.todoapp.data.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public interface ReposApi {
    String BASE_URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    LiveData<ApiResponse<List<Repo>>> getUserProject(@Path("user") String user,
                                                     @Query("type") String type,
                                                     @Query("sort") String sort,
                                                     @Query("direction") String direction,
                                                     @Query("page") int page);
}

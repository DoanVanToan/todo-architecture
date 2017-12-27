package com.toandoan.todoapp.di;

import com.toandoan.todoapp.data.source.remote.api.ReposApi;
import com.toandoan.todoapp.utils.LiveDataCallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by doan.van.toan on 12/26/17.
 */
@Module
@UserScope
public class AppModule {
    @Provides
    ReposApi providesReposApi(@Named("gsonConverterFactory")
                                      GsonConverterFactory gsonConverterFactory,
                              @Named("baseUrl") String baseUrl,
                              @Named("liveDataCallAdapterFactory")
                                      LiveDataCallAdapterFactory liveDataCallAdapterFactory){
       return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(liveDataCallAdapterFactory)
                .build()
                .create(ReposApi.class);
    }

    @Provides
    @Named("gsonConverterFactory")
    GsonConverterFactory providesGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Named("baseUrl")
    String providesBaseUrl(){
        return ReposApi.BASE_URL;
    }

    @Provides
    @Named("liveDataCallAdapterFactory")
    LiveDataCallAdapterFactory provideLiveDataCallAdapterFactory(){
        return new LiveDataCallAdapterFactory();
    }
}

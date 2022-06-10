package com.bootcamp.themoviedb.module

import android.content.Context
import com.bootcamp.api_service.RetrofitClient
import com.bootcamp.api_service.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit (@ApplicationContext context: Context) =
        RetrofitClient.client(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit) =
        retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideDiscoverService(retrofit: Retrofit) =
        retrofit.create(DiscoverService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailService(retrofit: Retrofit) =
        retrofit.create(MovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideMovieReviewService(retrofit: Retrofit) =
        retrofit.create(MovieReviewService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideoService(retrofit: Retrofit) =
        retrofit.create(MovieVideoService::class.java)

}
package com.bootcamp.themoviedb.module

import com.bootcamp.api_service.service.*
import com.bootcamp.api_service.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UsecaseModule {
    @Provides
    fun provideGenreUsecase(genreService: GenreService) =
        GenreUsecase(genreService)

    @Provides
    fun provideDiscoverUsecase(discoverService: DiscoverService) =
        DiscoverPagingUseCase(discoverService)

    @Provides
    fun provideMovieReviewPagingUsecase(movieReviewService: MovieReviewService) =
        MovieReviewPagingUsecase(movieReviewService)

    @Provides
    fun provideMovieDetailVideoUsecase(movieDetailService: MovieDetailService, movieVideoService: MovieVideoService) =
        MovieDetailVideoUsecase(movieDetailService, movieVideoService)

}
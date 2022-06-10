package com.bootcamp.api_service.usecase

import com.bootcamp.api_service.paging.MovieReviewPager
import com.bootcamp.api_service.service.MovieReviewService

class MovieReviewPagingUsecase(
    private val movieReviewService: MovieReviewService
) {
    operator fun invoke(movieId: Int) =
        MovieReviewPager.createPager(10, movieReviewService, movieId).flow
}
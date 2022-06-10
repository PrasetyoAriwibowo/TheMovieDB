package com.bootcamp.api_service.usecase

import com.bootcamp.api_service.paging.DiscoverMoviePager
import com.bootcamp.api_service.service.DiscoverService

class DiscoverPagingUseCase(
    private val discoverMovieService: DiscoverService
) {
    operator fun invoke(genres: String) = DiscoverMoviePager.createPager(
        10, discoverMovieService, genres
    ).flow
}
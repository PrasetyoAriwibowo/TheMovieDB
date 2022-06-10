package com.bootcamp.api_service.service

import com.bootcamp.api_service.Constants.API_KEY
import com.bootcamp.entity.discover.DiscoverMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {
    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("with_genres") genres: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ) : Response<DiscoverMovieResponse>
}
package com.bootcamp.api_service.service

import com.bootcamp.api_service.Constants.API_KEY
import com.bootcamp.entity.movie_detail.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : Response<MovieDetailsResponse>
}
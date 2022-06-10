package com.bootcamp.api_service.service

import com.bootcamp.api_service.Constants.API_KEY
import com.bootcamp.entity.movie_reviews.MovieReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : Response<MovieReviewsResponse>
}
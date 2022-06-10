package com.bootcamp.api_service.usecase

import com.bootcamp.api_service.service.MovieDetailService
import com.bootcamp.api_service.service.MovieVideoService
import com.bootcamp.common.AppResponse
import com.bootcamp.entity.movie_detail.MovieDetailsResponse
import com.bootcamp.entity.movie_video.MovieVideoResponse
import com.bootcamp.usecase.BaseUseCase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class MovieDetailVideoUsecase(
    private val movieDetailsService: MovieDetailService,
    private val movieVideoService: MovieVideoService
) : BaseUseCase() {
    operator fun invoke(movieId: Int) =
        callbackFlow<AppResponse<Pair<MovieDetailsResponse, MovieVideoResponse>>> {
        launch {
            send(AppResponse.loading())
            try {


            val dataMovieDetails = movieDetailsService.getMovieDetails(movieId)
            val dataMovieVideo = movieVideoService.getMovieVideo(movieId)

            if (dataMovieDetails.isSuccessful) {
                dataMovieDetails.body()?.let { movieDetailBody ->

                    if (dataMovieVideo.isSuccessful) {
                        dataMovieVideo.body()?.let { movieVideoBody ->
                            send(AppResponse.success(movieDetailBody to movieVideoBody))
                        }
                    }
                } ?: run {

                    send(AppResponse.errorSystem(Exception("Data Null")))
                }
            } else {

                send(
                    AppResponse.errorBackend(
                        dataMovieDetails.code(),
                        dataMovieDetails.errorBody()
                    )
                )
            }

        }catch (e: Exception){
                send(AppResponse.errorSystem(java.lang.Exception("Data Null")))
            }
            close()
        }
        awaitClose()
    }
}
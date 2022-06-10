package com.bootcamp.api_service.usecase

import com.bootcamp.api_service.service.GenreService
import com.bootcamp.common.AppResponse
import com.bootcamp.entity.genre.Genre
import com.bootcamp.usecase.BaseUseCase
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GenreUsecase(private val genreService: GenreService): BaseUseCase() {
    operator fun invoke() = flow <AppResponse<List<Genre>>>{
        try {
            emit(AppResponse.loading())
            val data = genreService.getGenreData()
            if (data.isSuccessful){
                data.body()?.let {
                    emit(AppResponse.success(it.genres))
                }?: run {
                    emit(AppResponse.errorBackend<List<Genre>>(data.code(), data.errorBody()))
                }

            }else{
                emit(AppResponse.errorBackend(data.code(), data.errorBody()))
            }
        }catch (e: Exception){
            emit(AppResponse.errorSystem(e))
        }
    }
}
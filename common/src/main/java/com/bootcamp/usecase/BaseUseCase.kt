package com.bootcamp.usecase

import com.bootcamp.common.AppResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

open class BaseUseCase {
    suspend fun <T> observeResponseData(
        scope: CoroutineScope,
        channel: ProducerScope<AppResponse<T>>? = null,
        flow: suspend () -> Response<T>,
        success: suspend (T) -> Unit,
        error: suspend (Exception?,ResponseBody?,Int) -> Unit,
        loading:  (suspend (T) -> Unit)? = null
    ) {
        val response = flow.invoke()
        if (response.isSuccessful) {
            response.body()?.let { data ->
                scope.launch {
                    success(
                        data
                    )
                    channel?.close()
                }
            }
        } else {
            scope.launch {
                error(
                   null,response.errorBody(), response.code()
                )
                channel?.close()
            }
        }
        channel?.awaitClose()
    }
}
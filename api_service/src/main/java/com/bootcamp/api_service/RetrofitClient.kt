package com.bootcamp.api_service

import android.content.Context
import android.util.Log
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.GanderInterceptor
import com.ashokvarma.gander.imdb.GanderIMDB
import com.bootcamp.api_service.Constants.BASE_URL
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun client(context: Context): Retrofit {
//        Gander.setGanderStorage(GanderIMDB.getInstance())
        return Retrofit.Builder()
            .client(OkHttpClient().newBuilder()
//                .addInterceptor(GanderInterceptor(context).showNotification(true))
                .addInterceptor(HttpLoggingInterceptor() {
                    Log.e("InetLog", it)
                    print("Log -> $it")
                })
                .addInterceptor {
                    try {
                        it.proceed(it.request())
                    } catch (e: Exception) {
                        Response.Builder().code(-2).body(
                            JsonObject().apply {
                                addProperty("error", "failure_client")
                            }.asString.toResponseBody("application/json".toMediaType())
                        ).build()
                    }
                }
                .build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
}
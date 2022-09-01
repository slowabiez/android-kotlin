package com.anara.helloworld

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://data.covid19.go.id/public/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface  CovidApiService {
    @GET("update.json")
    suspend fun getCovid(): CovidResponse
}

object CovidApi {
    val service: CovidApiService by lazy {
        retrofit.create(CovidApiService::class.java)
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }

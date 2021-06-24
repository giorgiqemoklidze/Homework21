package com.example.homework21.api

import com.example.homework21.model.News
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitRepository {

    @GET("v3/articles")
    suspend fun getNews(): Response<List<News>>

}
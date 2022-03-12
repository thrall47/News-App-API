package com.example.newsproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CallableInterface {


    @GET("/v2/top-headlines?apiKey=8a9389bc605243e0bba3d6ddfed96414")
    fun getNews(@Query("category") cat: String?, @Query("country") code: String?): Call<News>

}
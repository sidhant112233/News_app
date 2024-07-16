package com.example.news_app.Interface

import com.example.news_app.ApiModel
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")

  fun getnews(
      @Query("country")country:String,
      @Query("category")category:String,
      @Query("apiKey")apikey: String="59e051d55e3f4dbea1ee4c5d4ef1667e"
  ): retrofit2.Call<ApiModel>
}
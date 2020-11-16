package com.mahmoud.bashir.newsapp.api

import com.mahmoud.bashir.newsapp.models.NewsResponse
import com.mahmoud.bashir.newsapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi{
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode : String ="us",
        @Query("page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey:String = Constants.API_KEY
    ):Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("country")
        searchQuery : String ="us",
        @Query("page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey:String = Constants.API_KEY
    ):Response<NewsResponse>

}
package com.mahmoud.bashir.newsapp.repository

import com.mahmoud.bashir.newsapp.api.RetrofitInstance
import com.mahmoud.bashir.newsapp.roomdb.ArticleDatabase

class NewsRepository (
    val db: ArticleDatabase
){
    suspend fun getBreakingNews(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber:Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)
}
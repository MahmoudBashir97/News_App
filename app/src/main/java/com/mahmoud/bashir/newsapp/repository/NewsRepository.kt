package com.mahmoud.bashir.newsapp.repository

import com.mahmoud.bashir.newsapp.api.RetrofitInstance
import com.mahmoud.bashir.newsapp.models.Article
import com.mahmoud.bashir.newsapp.roomdb.ArticleDatabase

class NewsRepository (
    val db: ArticleDatabase
){
    suspend fun getBreakingNews(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber:Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upSert(article:Article) = db.articleDao().upsert(article)

    fun getSavedNews() = db.articleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.articleDao().deleteArticle(article)
}
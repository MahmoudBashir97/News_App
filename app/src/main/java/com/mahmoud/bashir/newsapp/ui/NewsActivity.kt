package com.mahmoud.bashir.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mahmoud.bashir.newsapp.R
import com.mahmoud.bashir.newsapp.repository.NewsRepository
import com.mahmoud.bashir.newsapp.roomdb.ArticleDatabase
import com.mahmoud.bashir.newsapp.viewModel.NewsViewModel
import com.mahmoud.bashir.newsapp.viewModel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    lateinit var viewModel : NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application,repository)

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        bottomNav.setupWithNavController(newsHostFragment.findNavController())


    }
}
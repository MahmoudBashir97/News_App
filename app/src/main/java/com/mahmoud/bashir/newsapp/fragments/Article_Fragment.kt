package com.mahmoud.bashir.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mahmoud.bashir.newsapp.R
import com.mahmoud.bashir.newsapp.ui.NewsActivity
import com.mahmoud.bashir.newsapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class Article_Fragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel : NewsViewModel
    val args:Article_FragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article =args.article

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }


    }
}
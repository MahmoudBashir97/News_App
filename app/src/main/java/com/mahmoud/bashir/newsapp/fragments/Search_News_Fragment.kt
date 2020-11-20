package com.mahmoud.bashir.newsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud.bashir.newsapp.R
import com.mahmoud.bashir.newsapp.adapters.NewsAdapter
import com.mahmoud.bashir.newsapp.ui.NewsActivity
import com.mahmoud.bashir.newsapp.util.Constants
import com.mahmoud.bashir.newsapp.util.Resource
import com.mahmoud.bashir.newsapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Search_News_Fragment : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel : NewsViewModel
    lateinit var newsAdapter:NewsAdapter
    private val TAG = "Search_News_Fragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as NewsActivity).viewModel
        setUpRecyclerView()

        newsAdapter.setOnClickListener {
            val bundle = Bundle().apply{
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_search_News_Fragment_to_article_Fragment,
                bundle
            )
        }
       // viewModel = (activity as NewsActivity).viewModel

        var job : Job?=null
        etSearch.addTextChangedListener {editable ->
            job?.cancel()
            job= MainScope().launch {
                delay(Constants.SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }


        viewModel.searchNews.observe(viewLifecycleOwner, Observer {
                response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let{
                            newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let{it ->
                        Log.e(TAG,"An error occured: $it")
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        rvSearchNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
package com.varseno.myapplication.views.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.varseno.myapplication.R
import com.varseno.myapplication.ViewModels.ArticleViewModel
import com.varseno.myapplication.models.MostView
import com.varseno.myapplication.views.adapters.ArticleAdapter
import java.nio.channels.Selector

class EmailedFragment : Fragment() {

    var adapter: ArticleAdapter? = null
    lateinit var mLayoutManager: LinearLayoutManager
    var articleList: List<MostView.Result>? = null
    lateinit var recyclerView: RecyclerView
    private lateinit var itemSelector: Selector
    private lateinit var model: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_emailed, container, false)
        recyclerView = view.findViewById(R.id.recyclerEmailedView);
        mLayoutManager = LinearLayoutManager(activity)
        model = ArticleViewModel()
        model.getArticles().observe(this, object : Observer<List<MostView.Result>> {
            override fun onChanged(articleList: List<MostView.Result>?) {
                // set Data to adapter here.
                recyclerView.setLayoutManager(LinearLayoutManager(context));
                adapter = ArticleAdapter(requireContext(), articleList!!)
                recyclerView.adapter = adapter
            }
        })
        return view
    }

}

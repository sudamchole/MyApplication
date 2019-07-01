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
import com.varseno.myapplication.views.adapters.ArticleAdapter
import com.varseno.myapplication.views.MainActivity
import android.arch.lifecycle.ViewModelProviders
import android.content.ClipData
import android.support.annotation.Nullable
import android.widget.Toast
import com.varseno.myapplication.ViewModels.ArticleViewModel
import com.varseno.myapplication.models.MostViewed
import android.util.Log
import java.nio.channels.Selector




class MostViewedFragment : Fragment() {

    var adapter: ArticleAdapter? = null
    lateinit var mLayoutManager: LinearLayoutManager
    var articleList: List<MostViewed>? = null
    lateinit var recyclerView: RecyclerView
    private lateinit var itemSelector: Selector
    private lateinit var model: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val  view:View = inflater.inflate(R.layout.fragment_most_viewed, container, false)
        recyclerView =view.findViewById(R.id.recyclerView);
        mLayoutManager = LinearLayoutManager(activity)
        Toast.makeText(requireContext(),"This is called",Toast.LENGTH_LONG).show()
        model= ArticleViewModel()
        model.getArticles().observe(this, object : Observer<List<MostViewed>> {
            override fun onChanged(articleList: List<MostViewed>?) {
                // set Data to adapter here.
                recyclerView.setLayoutManager(LinearLayoutManager(context));
                adapter = ArticleAdapter(requireContext(), articleList!!)
                recyclerView.adapter = adapter
            }
        })
        return view
    }


}
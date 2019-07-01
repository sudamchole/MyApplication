package com.varseno.myapplication.ViewModels

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import com.google.android.gms.common.api.Api
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.util.Log
import android.widget.Toast
import com.varseno.myapplication.models.MostViewed
import com.varseno.myapplication.requests.ApiClient
import com.varseno.myapplication.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Sudam Chole on 7/1/2019.
 */
class ArticleViewModel: ViewModel() {
    //this is the data that we will fetch asynchronously
    private var articleList: MutableLiveData<List<MostViewed>>? = null

    //we will call this method to get the data
    fun getArticles(): LiveData<List<MostViewed>> {
        //if the list is null
        if (articleList == null) {
            articleList = MutableLiveData<List<MostViewed>>()
            //we will load it asynchronously from server in this method
            loadArticles()
        }

        //finally we will return the list
        return articleList as MutableLiveData<List<MostViewed>>
    }


    //This method is using Retrofit to get the JSON data from URL
    private fun loadArticles() {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiClient::class.java)
        val call = api.getArticles()


        call.enqueue(object : Callback<List<MostViewed>> {
            override fun onResponse(call: Call<List<MostViewed>>, response: Response<List<MostViewed>>) {

                //finally we are setting the list to our MutableLiveData
                articleList?.setValue(response.body())
            }


            override fun onFailure(call: Call<List<MostViewed>>, t: Throwable) {
                Log.d("TAG", t.toString())
            }

        })
    }


}
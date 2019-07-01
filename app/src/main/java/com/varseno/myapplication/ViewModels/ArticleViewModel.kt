package com.varseno.myapplication.ViewModels

import android.arch.lifecycle.ViewModel
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.varseno.myapplication.models.MostView
import com.varseno.myapplication.requests.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.Interceptor
import okhttp3.OkHttpClient


/**
 * Created by Sudam Chole on 7/1/2019.
 */
class ArticleViewModel: ViewModel() {
    //this is the data that we will fetch asynchronously
    private var articleList: MutableLiveData<List<MostView.Result>>? = null

    //we will call this method to get the data
    fun getArticles(): LiveData<List<MostView.Result>> {
        //if the list is null
        if (articleList == null) {
            articleList = MutableLiveData<List<MostView.Result>>()
            //we will load it asynchronously from server in this method
            loadArticles()
        }

        //finally we will return the list
        return articleList as MutableLiveData<List<MostView.Result>>
    }


    //This method is using Retrofit to get the JSON data from URL
    private fun loadArticles() {

        // Append api-key parameter to every query
        val apiKeyInterceptor = { chain:Interceptor.Chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("api-key", ApiClient.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addNetworkInterceptor(StethoInterceptor())  // Enable Stetho network inspection
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiClient::class.java)
        val call = api.getArticles()


        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val data = response.body().toString()
                val articleMostView = Gson().fromJson(data, MostView::class.java)
                articleList?.setValue(articleMostView.results)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("TAG", t.toString())
            }

           /* override fun onResponse(call: Call<List<MostView.Result>>, response: Response<List<MostView.Result>>) {

                //finally we are setting the list to our MutableLiveData

            }


            override fun onFailure(call: Call<List<MostView.Result>>, t: Throwable) {

            }
*/
        })
    }


}
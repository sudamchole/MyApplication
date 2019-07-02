package com.varseno.myapplication.requests
import com.google.gson.JsonObject
import com.varseno.myapplication.models.MostView
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap




/**
 * Created by Sudam Chole on 7/1/2019.
 */
interface ApiClient {
    companion object {

        //var BASE_URL = "https://simplifiedcoding.net/demos/"
        var BASE_URL = "https://api.nytimes.com/svc/"
        var API_KEY="bbqf8LXJ4pbCb3fGqAw8X5EsFMkr5RRX"
    }

    @GET("mostpopular/v2/viewed/1.json")
    fun getArticles(): Call<JsonObject>


    @GET("mostpopular/v2/shared/1/facebook.json")
    fun getSharedArticles(): Call<JsonObject>


    @GET("mostpopular/v2/emailed/7.json")
    fun getEmails(): Call<JsonObject>

    @GET("search/v2/articlesearch.json")
    fun getArticles(@QueryMap query: Map<String, String>): Call<JsonObject>

}
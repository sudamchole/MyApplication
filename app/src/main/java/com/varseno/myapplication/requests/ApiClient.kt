package com.varseno.myapplication.requests
import com.google.gson.JsonObject
import com.varseno.myapplication.models.MostView
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by Sudam Chole on 7/1/2019.
 */
interface ApiClient {
    companion object {

        //var BASE_URL = "https://simplifiedcoding.net/demos/"
        var BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
        var API_KEY="bbqf8LXJ4pbCb3fGqAw8X5EsFMkr5RRX"
    }

    @GET("viewed/1.json")
    fun getArticles(): Call<JsonObject>


    @GET("shared/1/facebook.json")
    fun getSharedArticles(): Call<JsonObject>


    @GET("emailed/7.json")
    fun getEmails(): Call<JsonObject>


}
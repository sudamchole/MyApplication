package com.varseno.myapplication.requests
import com.google.android.gms.nearby.connection.Payload
import com.varseno.myapplication.models.MostViewed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


/**
 * Created by Sudam Chole on 7/1/2019.
 */
interface ApiClient {
    companion object {
        var BASE_URL = "https://simplifiedcoding.net/demos/"
    }

    @GET("marvel")
    fun getArticles(): Call<List<MostViewed>>


}
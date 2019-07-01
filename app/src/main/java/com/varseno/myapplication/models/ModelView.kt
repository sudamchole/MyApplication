package com.varseno.myapplication.models

class MostView{
    val copyright: String?=null
    val num_results: Int?=null
    val results: ArrayList<Result>?=null
    val status: String?=null

    inner class Result {
        val abstract: String? = null
        val adx_keywords: String? = null
        val asset_id: Long? = null
        val byline: String? = null
        val column: String? = null
        val id: Long? = null
        val published_date: String? = null
        val section: String? = null
        val source: String? = null
        val title: String? = null
        val type: String? = null
        val uri: String? = null
        val url: String? = null
        val views: Int? = null

    }
}

package com.varseno.myapplication.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.varseno.myapplication.R
import android.support.v4.app.NavUtils
import android.support.v7.app.ActionBar
import android.view.MenuItem


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
               finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}

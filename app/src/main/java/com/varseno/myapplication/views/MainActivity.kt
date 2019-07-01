package com.varseno.myapplication.views

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.varseno.myapplication.R
import com.varseno.myapplication.utils.replaceFragmenty
import com.varseno.myapplication.views.fragments.MostViewedFragment
import android.support.v4.app.Fragment
import android.R.attr.fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v4.app.FragmentManager
import com.varseno.myapplication.ViewModels.ArticleViewModel




class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var activeFragment = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        //Manually displaying the first fragment - one time only
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, MostViewedFragment())
        transaction.commit()
        activeFragment = "mostViewedFragment"

        val sharedViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)

        sharedViewModel.getArticles().observe(this, Observer {
            it?.let {

            }
        })
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.nav_search_article -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_most_viewed -> {
                if (!activeFragment.equals("mostViewedFragment")) {
                    selectedFragment = MostViewedFragment()
                    activeFragment = "mostViewedFragment"
                }
            }
            R.id.nav_most_shared -> {

            }
            R.id.nav_most_emailed -> {

            }
        }


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
        fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {

        val transaction = manager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()

    }

}

package com.varseno.myapplication.views

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.varseno.myapplication.R
import com.varseno.myapplication.views.fragments.MostViewedFragment
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import com.varseno.myapplication.ViewModels.ArticleViewModel
import com.varseno.myapplication.views.fragments.EmailedFragment
import com.varseno.myapplication.views.fragments.SharedFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Handler
import android.view.View
import kotlinx.android.synthetic.main.app_bar_main.*






class MainActivity : AppCompatActivity() {

    companion object {
        // index to identify current nav menu item
        var navItemIndex = 0

        // tags used to attach the fragments
         val TAG_POPULAR = "popular"
         val TAG_SHARED = "shared"
         val TAG_EMAILED = "emailed"


        var CURRENT_TAG = TAG_POPULAR
    }


    // flag to load home fragment when user presses back key
    private val shouldLoadHomeFragOnBackPress = true
    // toolbar titles respected to selected nav menu item
    private var activityTitles: Array<String>? = null

    lateinit var navView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
// load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles)


        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_POPULAR;
            loadHomeFragment();
        }

        val sharedViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)

        sharedViewModel.getArticles().observe(this, Observer {
            it?.let {

            }
        })
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private fun loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu()

        // set toolbar title
        setToolbarTitle()
// Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app

            // update the main content by replacing fragments
            val fragment = getHomeFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            fragmentTransaction.replace(R.id.container, fragment, CURRENT_TAG)
            fragmentTransaction.commitAllowingStateLoss()


        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (supportFragmentManager.findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers()

            return
        }

        //Closing drawer on item click
        drawerLayout.closeDrawers()

        // refresh toolbar menu
        invalidateOptionsMenu()
    }

    private fun setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            // This method will trigger on item Click of navigation menu
            //Check to see which item was being clicked and perform appropriate action
            when (menuItem.itemId) {
                //Replacing the main content with ContentFragment Which is our Inbox View;
                R.id.nav_most_viewed -> {
                    navItemIndex = 0
                    CURRENT_TAG = TAG_POPULAR
                }
                R.id.nav_most_shared -> {
                    navItemIndex = 1
                    CURRENT_TAG = TAG_SHARED
                }
                R.id.nav_most_emailed -> {
                    navItemIndex = 2
                    CURRENT_TAG = TAG_EMAILED
                }

               R.id.nav_search_article -> {
                    // launch new intent instead of loading fragment
                    startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                    drawerLayout.closeDrawers()
                    return@OnNavigationItemSelectedListener true
                }
                else -> navItemIndex = 0
            }

            //Checking if the item is in checked state or not, if not make it in checked state
            if (menuItem.isChecked) {
                menuItem.isChecked = false
            } else {
                menuItem.isChecked = true
            }
          //  menuItem.isChecked = true

            loadHomeFragment()

            return@OnNavigationItemSelectedListener true
        })


        val actionBarDrawerToggle =
            object : ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            ) {

                override fun onDrawerClosed(drawerView: View) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                    super.onDrawerOpened(drawerView)
                }
            }

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle)

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState()

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
            return
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0
                CURRENT_TAG = TAG_POPULAR
                loadHomeFragment()
                return
            }
        }

        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
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


    private fun selectNavMenu() {

        navView!!.getMenu().getItem(navItemIndex).setChecked(true)
    }

    private fun setToolbarTitle() {
        setTitle(activityTitles!![navItemIndex])
    }

    private fun getHomeFragment(): Fragment {
        when (navItemIndex) {
            0 -> {
                // Popular
                return MostViewedFragment()
            }
            1 -> {
                // shared
                return SharedFragment()
            }
            2 -> {
                // emailed
                return EmailedFragment()
            }

            else -> return MostViewedFragment()
        }
    }
}

package com.safari.drfoot.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.Menu
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigator
import com.bumptech.glide.Glide
import com.safari.drfoot.R
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.utility.SharedPreferencesHelper
import com.safari.drfoot.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.nav_header_nav.*

class NavActivity : InjectorActivity<MainActivityViewModel>() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        viewModel.init()
        val avatar = nav_view.getHeaderView(0).findViewById(R.id.avatar) as ImageView
        val nameText = nav_view.getHeaderView(0).findViewById(R.id.headertText1) as TextView
        val emailText = nav_view.getHeaderView(0).findViewById(R.id.headertText2) as TextView
        viewModel.loadMe().observe(this, Observer {
            Glide.with(applicationContext).load(it?.imageLocal).into(avatar)
            nameText.text = it?.name
            emailText.text = it?.email
        })
        SharedPreferencesHelper.setString(applicationContext, "parent", "personId", "-1")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            viewModel.logout()
            com.safari.drfoot.utility.Navigator.withouthBundle().changeActivity(this, Splash::class.java, true)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

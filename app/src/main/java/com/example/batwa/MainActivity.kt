package com.example.batwa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Log.d("hamza",supportFragmentManager.findFragmentById(R.id.fragment_container_view).toString())



//        Getting the nav controller
        var navHost: NavHostFragment=supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController=navHost.findNavController()

//        Initializing the app bar configuation.
        appBarConfiguration= AppBarConfiguration(setOf(R.id.homeFragment), drawer_layout)

//        Setting the default action bar
        setSupportActionBar(toolbar_main)

//        Integrating nav controller with the action bar
        setupActionBarWithNavController(navController,appBarConfiguration)

//        Integrating nav drawer with the nav controller
        nav_drawer.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
//        If I do not provide the appBarConfiguration as parameter here the hamburger icon won't do anything.
        return navController.navigateUp(appBarConfiguration)||super.onSupportNavigateUp()
    }
}
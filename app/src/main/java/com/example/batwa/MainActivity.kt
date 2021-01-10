package com.example.batwa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController

class MainActivity : AppCompatActivity() {

    private lateinit var navCompatActivity: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("hamza",supportFragmentManager.findFragmentById(R.id.fragment_container_view).toString())

    }
}
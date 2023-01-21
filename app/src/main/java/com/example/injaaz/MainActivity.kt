package com.example.injaaz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.injaaz.R
import com.example.injaaz.data.data_source.DataSource.tasks
import com.example.injaaz.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.background = null
        navView.menu.getItem(3).isEnabled = false

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // The code is commented because there's no action bar on top (the theme used doesn't contain one)
        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tasks, R.id.navigation_schedule, R.id.navigation_me
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)*/
        navView.setupWithNavController(navController)

        binding.newTaskFab.setOnClickListener {
            if (navController.currentDestination?.id == R.id.navigation_tasks) {
                navController.navigate(R.id.action_navigation_tasks_to_newTaskFragment)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp() || super.onSupportNavigateUp()
    }
}
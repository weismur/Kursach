package com.example.testtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testtest.databinding.ActivityAboutProgramBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutProgram : AppCompatActivity() {
    private lateinit var binding: ActivityAboutProgramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityAboutProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_book_list)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_developers, R.id.nav_info,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (navView.selectedItemId == R.id.nav_developers) {
            supportActionBar?.title = "Разработчики"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        if (navView.selectedItemId == R.id.nav_info) {
            supportActionBar?.title = "Информация"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
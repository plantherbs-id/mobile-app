package com.plantherbs.app.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.plantherbs.app.R
import com.plantherbs.app.ViewModelFactory
import com.plantherbs.app.databinding.ActivityMainBinding
import com.plantherbs.app.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getUser().observe(this) { user ->
            if (user.isLogin) {
                val navController = findNavController(R.id.nav_host_fragment)
                binding.bottomNavView.setupWithNavController(navController)
            } else {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }
    }
}
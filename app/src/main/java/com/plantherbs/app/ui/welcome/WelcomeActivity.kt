package com.plantherbs.app.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.plantherbs.app.databinding.ActivityWelcomeBinding
import com.plantherbs.app.ui.login.LoginActivity
import com.plantherbs.app.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val toLoginPage = Intent(this, LoginActivity::class.java)
            startActivity(toLoginPage)
        }

        binding.buttonRegister.setOnClickListener {
            val toRegisterPage = Intent(this, RegisterActivity::class.java)
            startActivity(toRegisterPage)
        }
    }
}
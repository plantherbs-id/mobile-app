package com.plantherbs.app.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.plantherbs.app.R
import com.plantherbs.app.ui.login.LoginActivity
import com.plantherbs.app.ui.network.retrofit.RetrofitClient
import com.plantherbs.app.ui.network.service.ApiService

class RegisterActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        apiService = RetrofitClient.retrofit.create(ApiService::class.java)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        val btnRegister: Button = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener {
            // Get values from input fields
            val fullName = findViewById<EditText>(R.id.input_nama_lengkap).text.toString()
            val email = findViewById<EditText>(R.id.input_email).text.toString()
            val phoneNumber = findViewById<EditText>(R.id.input_nomor_telepon).text.toString()
            val password = findViewById<EditText>(R.id.input_password).text.toString()

            // Call the registerUser function from ViewModel
            registerViewModel.registerUser(apiService, fullName, email, phoneNumber, password)
        }

        val tvToLogin: TextView = findViewById(R.id.tv_tologin)
        tvToLogin.setOnClickListener {
            // Intent to move to the login page
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

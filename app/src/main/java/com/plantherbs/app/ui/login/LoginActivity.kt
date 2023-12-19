package com.plantherbs.app.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.plantherbs.app.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.plantherbs.app.network.ApiResponse
import com.plantherbs.app.network.ApiService
import com.plantherbs.app.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var apiService: ApiService
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi Retrofit
        apiService = RetrofitClient.retrofit.create(ApiService::class.java)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        emailEditText = findViewById(R.id.input_login_email)
        passwordEditText = findViewById(R.id.input_login_password)

        val tombolLogin: Button = findViewById(R.id.btn_login)

        tombolLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Melakukan login
                viewModel.loginUser(apiService, email, password)
            } else {
                Toast.makeText(this, "Silakan masukkan email dan password", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginResult.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                // TODO: Handle login sukses (contohnya, pindah ke aktivitas utama)
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login gagal. Kredensial tidak valid", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
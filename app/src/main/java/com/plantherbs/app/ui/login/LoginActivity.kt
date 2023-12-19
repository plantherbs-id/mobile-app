package com.plantherbs.app.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.plantherbs.app.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi Retrofit
        apiService = RetrofitClient.retrofit.create(ApiService::class.java)

        val tombolLogin: Button = findViewById(R.id.btn_login)

        tombolLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Melakukan login
                lakukanLogin(email, password)
            } else {
                Toast.makeText(this, "Silakan masukkan email dan password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun lakukanLogin(email: String, password: String) {
        val panggilan: Call<ApiResponse> = apiService.login(email, password)
        panggilan.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(panggilan: Call<ApiResponse>, respons: Response<ApiResponse>) {
                if (respons.isSuccessful && respons.body() != null) {
                    // Menghandle login sukses
                    val responsApi: ApiResponse = respons.body()!!
                    if (responsApi.isSuccess()) {
                        Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                        // TODO: Menghandle login sukses (contohnya, pindah ke aktivitas utama)
                    } else {
                        Toast.makeText(this@LoginActivity, "Login gagal. Kredensial tidak valid", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login gagal. Silakan coba lagi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(panggilan: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Kesalahan jaringan. Silakan coba lagi", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
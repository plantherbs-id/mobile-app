package com.plantherbs.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plantherbs.app.network.ApiResponse
import com.plantherbs.app.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    fun loginUser(apiService: ApiService, email: String, password: String) {
        val call: Call<ApiResponse> = apiService.login(email, password)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val apiResponse: ApiResponse = response.body()!!
                    _loginResult.value = apiResponse.isSuccess()
                } else {
                    _loginResult.value = false
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                _loginResult.value = false
            }
        })
    }
}
package com.plantherbs.app.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plantherbs.app.network.response.ApiResponse
import com.plantherbs.app.ui.network.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean>
        get() = _registerResult

    fun registerUser(
        apiService: ApiService,
        fullName: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        val call: Call<ApiResponse> = apiService.register(fullName, email, phoneNumber, password)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val apiResponse: ApiResponse = response.body()!!
                    _registerResult.value = apiResponse.isSuccess()

                    // Tambahkan pemanggilan _registerResult.postValue(true) di sini jika perlu
                } else {
                    _registerResult.value = false
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                _registerResult.value = false
            }
        })
    }
}

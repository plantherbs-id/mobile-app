package com.plantherbs.app.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.plantherbs.app.data.repository.Repository
import com.plantherbs.app.data.remote.datastore.UserModel
import com.plantherbs.app.data.remote.datastore.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: Repository) : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        _isLoading.postValue(true)

        viewModelScope.launch {
            try {
                val response = repository.login(email, password)

                saveUser(
                    UserModel(
                        response.results.userId,
                        response.results.name,
                        response.results.email,
                        response.results.token,
                        true
                    )
                )

                _isLoading.postValue(false)
                _loginResponse.postValue(response)
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
                val errorMessage = errorBody.message

                _isLoading.postValue(false)
                _loginResponse.postValue(errorBody)

                Log.d(TAG, "Login Error: $errorMessage")
            }
        }
    }

    private fun saveUser(user: UserModel) {
        viewModelScope.launch {
            repository.saveUser(user)
        }
    }

    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }
}
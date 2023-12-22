package com.plantherbs.app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.plantherbs.app.data.remote.datastore.UserModel
import com.plantherbs.app.data.remote.datastore.UserPreferences
import com.plantherbs.app.model.DefaultResponse
import com.plantherbs.app.model.HerbResponse
import com.plantherbs.app.model.LoginResult
import com.plantherbs.app.model.UserResponse
import com.plantherbs.app.network.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import retrofit2.Response
import kotlin.Result

class Repository private constructor(
    private val ApiService: ApiService,
    private val userPreferences: UserPreferences,
) {
    fun getSession(): Flow<LoginResult> {
        return userPreferences.getSession()
    }

    suspend fun register(name: String, email: String, password: String) =
        ApiService.register(name, email, password)

    suspend fun login(email: String, password: String) =
        ApiService.login(email, password)

    fun getUser() = userPreferences.getUser().asLiveData()

    suspend fun saveUser(user: UserModel) = userPreferences.saveUser(user)

    suspend fun logout() = userPreferences.logout()

    fun getUserById(userId: String): LiveData<Result<UserResponse>> = liveData {
        emit(Result.Success(Response))
        try {
            val response = ApiService.getUserById(userId)
            emit(Result.success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
            emit(com.plantherbs.app.data.Result.Error(errorResponse.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            ApiService: ApiService,
            userPreferences: UserPreferences
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(ApiService, userPreferences)
            }.also { instance = it }
    }
}
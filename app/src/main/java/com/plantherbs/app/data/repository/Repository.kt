package com.plantherbs.app.data.repository

import androidx.lifecycle.asLiveData
import com.plantherbs.app.data.remote.datastore.UserModel
import com.plantherbs.app.data.remote.datastore.UserPreferences
import com.plantherbs.app.network.ApiService

class Repository private constructor(
    private val ccApiService: ApiService,
    private val userPreferences: UserPreferences,
) {

    suspend fun register(name: String, email: String, password: String) =
        ccApiService.register(name, email, password)

    suspend fun login(email: String, password: String) =
        ccApiService.login(email, password)

    fun getUser() = userPreferences.getUser().asLiveData()

    suspend fun saveUser(user: UserModel) = userPreferences.saveUser(user)

    suspend fun logout() = userPreferences.logout()

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
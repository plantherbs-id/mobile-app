package com.plantherbs.app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.plantherbs.app.data.remote.datastore.UserModel
import com.plantherbs.app.data.remote.datastore.UserPreferences
<<<<<<< Updated upstream
import com.plantherbs.app.data.remote.datastore.response.AddBookmarkResponse
import com.plantherbs.app.data.remote.datastore.response.DetailResponse
import com.plantherbs.app.data.remote.datastore.response.HerbsResponse
import com.plantherbs.app.model.DefaultResponse
import com.plantherbs.app.model.HerbResponse
=======
import com.plantherbs.app.data.remote.datastore.response.UserResponse
>>>>>>> Stashed changes
import com.plantherbs.app.model.LoginResult
import com.plantherbs.app.model.UserResponse
import com.plantherbs.app.network.ApiService
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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

<<<<<<< Updated upstream
    fun getAllHerbs(): LiveData<Result<HerbsResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllHerbs()
=======
    fun getUserById(userId: String): LiveData<Result<UserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUserById(userId)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
            emit(Result.Error(errorResponse.message.toString()))
        }
    }

    fun getAllHerbs(): LiveData<Result<FoodResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllFoods()
>>>>>>> Stashed changes
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
            emit(Result.Error(errorResponse.message.toString()))
        }
    }

    fun getHerbsById(id: Int): LiveData<Result<DetailResponse>> = liveData {
        emit(Result.Loading)
        try {
<<<<<<< Updated upstream
            val response = apiService.getHerbsById(id)
=======
            val response = apiService.getFoodById(id)
>>>>>>> Stashed changes
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
            emit(Result.Error(errorResponse.message.toString()))
        }
    }

    fun getSpesificBookmark(userId: String, foodId: String): LiveData<Result<DetailResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getSpesificBookmark(userId, foodId)
                emit(Result.Success(response))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
                emit(Result.Error(errorResponse.message.toString()))
            }
        }

    fun addBookmark(userId: String, foodId: String): LiveData<Result<AddBookmarkResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.addBookmark(userId, foodId)
                emit(Result.Success(response))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
                emit(Result.Error(errorResponse.message.toString()))
            }
        }

    fun deleteBookmark(userId: String, bookmarkId: String): LiveData<Result<DefaultResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.deleteBookmark(userId, bookmarkId)
                emit(Result.Success(response))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
                emit(Result.Error(errorResponse.message.toString()))
            }
        }

    fun getHerbsByKeywoord(keyword: String): LiveData<Result<HerbsResponse>> = liveData {
        emit(Result.Loading)
        try {
<<<<<<< Updated upstream
            val response = apiService.getHerbsByKeyword(keyword)
=======
            val response = apiService.getFoodByKeyword(keyword)
>>>>>>> Stashed changes
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
            emit(Result.Error(errorResponse.message.toString()))
        }
    }

<<<<<<< Updated upstream
    fun scanHerbs(herbsName: String): LiveData<Result<HerbsResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.scanHerbs(herbsName)
=======
    fun scanHerbs(foodName: String): LiveData<Result<HerbsResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.scanFood(foodName)
>>>>>>> Stashed changes
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DefaultResponse::class.java)
            emit(Result.Error(errorResponse.message.toString()))
        }
    }

<<<<<<< Updated upstream

}
=======
}

>>>>>>> Stashed changes

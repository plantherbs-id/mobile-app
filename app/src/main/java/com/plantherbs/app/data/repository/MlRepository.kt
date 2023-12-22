package com.plantherbs.app.data.repository

import com.plantherbs.app.data.remote.retrofit.ML.MlApiService
import okhttp3.MultipartBody

class MlRepository private constructor(
    private val mlApiService: MlApiService,
) {

    suspend fun detectionImage(file: MultipartBody.Part) = mlApiService.detectionImage(file)

    companion object {
        @Volatile
        private var instance: MlRepository? = null

        fun getInstance(
            mlApiService: MlApiService
        ): MlRepository =
            instance ?: synchronized(this) {
                instance ?: MlRepository(mlApiService)
            }.also { instance = it }
    }
}
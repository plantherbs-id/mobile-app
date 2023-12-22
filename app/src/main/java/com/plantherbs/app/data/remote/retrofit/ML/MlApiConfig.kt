package com.plantherbs.app.data.remote.retrofit.ML

import com.plantherbs.app.data.remote.response.DetectionResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

object MlApiConfig {
    @Multipart
    @POST("detectImage")
    suspend fun detectionImage(
        @Part file: MultipartBody.Part
    ): DetectionResponse

    {
    }
}
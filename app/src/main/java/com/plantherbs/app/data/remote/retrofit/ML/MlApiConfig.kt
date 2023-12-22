package com.plantherbs.app.data.remote.retrofit.ML

import com.plantherbs.app.data.remote.response.DetectionResponse
import de.hdodenhof.circleimageview.BuildConfig
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.util.concurrent.TimeUnit

object MlApiConfig {

    fun getApiServiceMl(): MlApiService {

        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://predict-api-msa6gk7a6q-uc.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(MlApiService::class.java)
    }

    @Multipart
    @POST("detectImage")
    suspend fun detectionImage(
        @Part file: MultipartBody.Part
    ): DetectionResponse
 4b7b171821d5d463f2a8daea0fa81c5ff14ef19a
}
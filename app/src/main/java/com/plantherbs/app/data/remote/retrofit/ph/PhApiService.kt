package com.plantherbs.app.data.remote.retrofit.ph

import com.plantherbs.app.data.remote.response.CheckPriceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhApiService {

    @GET("Home/GetGridData1")
    fun getRicePrice(
        @Query("tanggal") tanggal: String,
        @Query("plantherbs") plantherbs: String,
        @Query("priceType") priceType: Int,
        @Query("provId") provId: Int
    ): Call<CheckPriceResponse>
}
package com.plantherbs.app.data.repository

class PhRepository private constructor(
    private val biApiService: PhApiService
) {

    fun getRicePrice(tanggal: String, commodity: String, priceType: Int, provId: Int) =
        biApiService.getRicePrice(tanggal, commodity, priceType, provId)

    companion object {
        @Volatile
        private var instance: PhRepository? = null

        fun getInstance(
            phApiService: PhApiService
        ): PhRepository =
            instance ?: synchronized(this) {
                instance ?: PhRepository(phApiService)
            }.also { instance = it }
    }
}
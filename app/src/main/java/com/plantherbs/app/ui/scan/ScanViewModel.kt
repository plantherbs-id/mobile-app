package com.plantherbs.app.ui.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class ScanViewModel : ViewModel() {

    private val baseUrl = "https://predict-api-msa6gk7a6q-uc.a.run.app/"
    private val jsonMediaType = "application/json".toMediaType()

    fun makeApiRequest(jsonBody: String) {
        viewModelScope.launch {
            val requestBody = jsonBody.toRequestBody(jsonMediaType)

            val request: Request = Request.Builder()
                .url(baseUrl)
                .post(requestBody)
                .build()

            val client = OkHttpClient()
            val response = client.newCall(request).execute()

            // Handle the response as needed
            val responseBody = response.body?.string()
            // Do something with responseBody

            // Make sure to close the response to avoid resource leaks
            response.close()
        }
    }
}

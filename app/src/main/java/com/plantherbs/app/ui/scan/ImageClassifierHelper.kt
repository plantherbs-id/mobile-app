package com.plantherbs.app.ui.scan

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class ImageClassifierHelper {

    private val baseUrl ="https://predict-api-msa6gk7a6q-uc.a.run.app/"
    private val jsonMediaType = "application/json".toMediaType()

    // Replace this function with your actual image classification logic
    fun classifyImage(imageData: ByteArray): String {
        val requestBody = imageData.toRequestBody(jsonMediaType)

        val request: Request = Request.Builder()
            .url(baseUrl)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        return response.body?.string() ?: "Error: Empty response"
    }
}

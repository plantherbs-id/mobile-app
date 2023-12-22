package com.plantherbs.app.ui.scan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.plantherbs.app.R
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class ScanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        val url = "https://predict-api-msa6gk7a6q-uc.a.run.app/"
        val jsonMediaType = "application/json".toMediaType()
        val requestBody = "Your JSON Body Here".toRequestBody(jsonMediaType)

        val request: Request = Request.Builder()
            .url(url)
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

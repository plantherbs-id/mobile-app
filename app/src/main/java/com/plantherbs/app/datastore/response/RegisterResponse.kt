package com.plantherbs.app.datastore.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String
)

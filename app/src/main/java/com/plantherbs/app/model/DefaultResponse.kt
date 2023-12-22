package com.plantherbs.app.model

import com.google.gson.annotations.SerializedName

data class DefaultResponse(

    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

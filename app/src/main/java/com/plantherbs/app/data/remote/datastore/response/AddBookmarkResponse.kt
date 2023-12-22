package com.plantherbs.app.data.remote.datastore.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddBookmarkResponse(

    @field:SerializedName("data")
    val data: AddBookmarkData? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
) : Parcelable

@Parcelize
data class AddBookmarkData(

    @field:SerializedName("foodsId")
    val foodsId: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
) : Parcelable

package com.plantherbs.app.data.remote.datastore.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    val token: String,
    val data: Data? = null,
    val status: Boolean
) : Parcelable

@Parcelize
data class Data(
    val id: Int,
    val fullname: String,
    val email: String,
    val username: String,
    val urlImage: String
) : Parcelable

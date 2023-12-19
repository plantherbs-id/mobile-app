package com.plantherbs.app.network

data class ApiResponse(val sukses: Boolean, val pesan: String){
    fun isSuccess(): Boolean {
        return sukses
    }
}
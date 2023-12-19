package com.plantherbs.app.network.response

data class ApiResponse(val sukses: Boolean, val pesan: String){
    fun isSuccess(): Boolean {
        return sukses
    }
}



package com.plantherbs.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.plantherbs.app.data.repository.Repository
import com.plantherbs.app.data.remote.datastore.UserModel

class MainViewModel(private val ccRepository: Repository) : ViewModel() {

    fun getUser(): LiveData<UserModel> = ccRepository.getUser()
}
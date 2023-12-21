package com.plantherbs.app.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plantherbs.app.data.remote.datastore.UserModel
import com.plantherbs.app.data.repository.Repository
import kotlinx.coroutines.launch

class AccountViewModel(private val Repository: Repository) : ViewModel() {

    fun getUser(): LiveData<UserModel> = Repository.getUser()

    fun logout() {
        viewModelScope.launch {
            Repository.logout()
        }
    }
}
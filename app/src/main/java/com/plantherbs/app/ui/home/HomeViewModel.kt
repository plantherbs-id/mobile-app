package com.plantherbs.app.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plantherbs.app.data.repository.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val isBookmarked = MutableLiveData<Boolean>()
    val bookmarkId = MutableLiveData<String>()
    private val _keyword = MutableLiveData<String>()
    val keyword: LiveData<String>
        get() = _keyword
    val location = MutableLiveData<Location>()

    fun getSession(): LiveData<LoginResult> {
        return repository.getSession().asLiveData()
    }

    fun getUserById(userId: String) = repository.getUserById(userId)

    fun getAllHerbs() = repository.getAllHerbs()

    fun getHerbById(id: Int) = repository.getHerbById(id)

    fun setKeyword(keyword: String) {
        _keyword.value = keyword
        getHerbByKeyword(_keyword.value.toString())
    }

    fun getHerbByKeyword(keyword: String) = repository.getHerbByKeyword(keyword)

    fun getSpecificBookmark(userId: String, herbId: String) = repository.getSpecificBookmark(userId, herbId)

    fun addBookmark(userId: String, herbId: String) = repository.addBookmark(userId, herbId)

    fun deleteBookmark(userId: String, bookmarkId: String) = repository.deleteBookmark(userId, bookmarkId)
}

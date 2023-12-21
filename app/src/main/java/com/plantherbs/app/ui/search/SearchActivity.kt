package com.plantherbs.app.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SearchActivity {

    private val isLoading = MutableLiveData<Boolean>()
    val getIsLoading: LiveData<Boolean> = isLoading

    private var filterData: MutableLiveData<String> = MutableLiveData()
    private var selectedUserId
            : String? = null
}
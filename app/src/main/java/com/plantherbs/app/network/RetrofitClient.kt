package com.plantherbs.app.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.plantherbs.app.data.repository.Repository
import com.plantherbs.app.data.remote.datastore.UserPreferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

object Injection {

    fun ccProvideRepository(context: Context): Repository {
        val pref = UserPreferences.getInstance(context.dataStore)
        val ApiService = ApiConfig.getApiService()
        return Repository.getInstance(ApiService, pref)
    }
}

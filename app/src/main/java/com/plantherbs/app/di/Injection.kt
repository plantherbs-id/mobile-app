package com.plantherbs.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.plantherbs.app.data.local.PlantHerbsRoomDatabase
import com.plantherbs.app.data.remote.datastore.UserPreferences
import com.plantherbs.app.data.remote.retrofit.ML.MlApiConfig
import com.plantherbs.app.data.remote.retrofit.cc.CcApiConfig
import com.plantherbs.app.data.remote.retrofit.ph.PhApiConfig
import com.plantherbs.app.data.remote.retrofit.ph.PhApiService
import com.plantherbs.app.data.repository.CcRepository
import com.plantherbs.app.data.repository.MlRepository
import com.plantherbs.app.data.repository.PhRepository
import com.plantherbs.app.data.repository.PlantHerbsRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

object Injection {

    fun ccProvideRepository(context: Context): CcRepository {
        val pref = UserPreferences.getInstance(context.dataStore)
        val ccApiService = CcApiConfig.getApiServiceCc()
        return CcRepository.getInstance(ccApiService, pref)
    }

    fun mlProvideRepository(): MlRepository {
        val mlApService = MlApiConfig.getApiServiceMl()
        return MlRepository.getInstance(mlApService)
    }

    fun plantherbsProvideRepository(context: Context): PlantHerbsRepository {
        val database = PlantHerbsRoomDatabase.getDatabase(context)
        val dao = database.plantherbsDao()
        return PlantHerbsRepository.getInstance(dao)
    }

    fun biProvideRepository(): PlantHerbsRepository {
        val biApiService = PhApiConfig.getApiServiceBi()
        return PlantHerbsRepository.getInstance(PhApiService)
    }
}
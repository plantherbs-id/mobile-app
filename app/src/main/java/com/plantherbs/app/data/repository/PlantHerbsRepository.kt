package com.plantherbs.app.data.repository

import androidx.lifecycle.LiveData
import com.plantherbs.app.data.local.PlantHerbsDao
import com.plantherbs.app.data.local.entity.ResultDetection
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PlantHerbsRepository private constructor(
    private val plantherbsDao: PlantHerbsDao,
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
) {

    fun getAllResult(): LiveData<List<ResultDetection>> = plantherbsDao.getAllResult()

    fun insertResult(result: ResultDetection) {
        executorService.execute {plantherbsDao.insertResult(result) }
    }

    fun deleteResult(result: ResultDetection) {
        executorService.execute {plantherbsDao.deleteResult(result) }
    }

    companion object {
        @Volatile
        private var instance: PlantHerbsRepository? = null

        fun getInstance(
            berasilDao: PlantHerbsDao
        ): PlantHerbsRepository =
            instance ?: synchronized(this) {
                instance ?: PlantHerbsRepository(berasilDao)
            }.also { instance = it }
    }
}
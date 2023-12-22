package com.plantherbs.app.data.local

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.plantherbs.app.data.local.entity.ResultDetection
interface PlantHerbsDao {

    @Query("SELECT * FROM results ORDER BY id DESC")
    fun getAllResult(): LiveData<List<ResultDetection>>

    @Insert
    fun insertResult(resultDetection: ResultDetection)

    @Delete
    fun deleteResult(resultDetection: ResultDetection)
}
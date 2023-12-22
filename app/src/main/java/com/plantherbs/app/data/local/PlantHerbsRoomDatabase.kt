package com.plantherbs.app.data.local

import android.content.Context
import androidx.room.Room

abstract class PlantHerbsRoomDatabase : RoomDatabase() {

    abstract fun plantherbsDao(): PlantHerbsDao

    companion object {
        @Volatile
        private var INSTANCE: PlantHerbsRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): PlantHerbsRoomDatabase {
            if (INSTANCE == null) {
                synchronized(PlantHerbsRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlantHerbsRoomDatabase::class.java,
                        "berasil_database"
                    ).build()
                }
            }
            return INSTANCE as PlantHerbsRoomDatabase
        }
    }
}
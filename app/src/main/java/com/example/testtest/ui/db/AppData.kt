package com.example.project_test.ui


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Entity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultsDao(): ResultsDao
}

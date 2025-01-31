package com.example.listafilmow.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listafilmow.data.local.dao.MovieDao
import com.example.listafilmow.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

package com.example.listafilmow.data.remote.repository

import android.content.Context
import androidx.room.Room
import com.example.listafilmow.data.local.db.AppDatabase
import com.example.listafilmow.data.local.dao.MovieDao
import com.example.listafilmow.data.model.MovieEntity
import com.example.listafilmow.data.model.MovieResponse
import com.example.listafilmow.data.remote.api.RetrofitClient

class MovieRepository(context: Context) {

    private val db: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "movies.db"
    ).build()

    private val movieDao: MovieDao = db.movieDao()

    suspend fun searchMovies(apiKey: String, query: String): MovieResponse {
        return RetrofitClient.api.searchMovies(apiKey, query)
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun getSavedMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    suspend fun deleteMovie(movieId: Int) {
        movieDao.deleteMovie(movieId)
    }
}

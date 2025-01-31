package com.example.listafilmow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.listafilmow.data.model.Movie
import com.example.listafilmow.data.remote.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepository(application.applicationContext)

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()

    fun searchMovies(apiKey: String, query: String) {
        viewModelScope.launch {
            val response = repository.searchMovies(apiKey, query)
            _movies.value = response.results
        }
    }
}

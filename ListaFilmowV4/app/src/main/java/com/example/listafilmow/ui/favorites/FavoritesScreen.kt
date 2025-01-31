package com.example.listafilmow.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listafilmow.data.model.MovieEntity
import com.example.listafilmow.data.remote.repository.MovieRepository
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(repository: MovieRepository, onMovieClick: (MovieEntity) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var favoriteMovies by remember { mutableStateOf(emptyList<MovieEntity>()) }

    LaunchedEffect(Unit) {
        favoriteMovies = repository.getSavedMovies()
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(favoriteMovies) { movie ->
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onMovieClick(movie) }) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = movie.title, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        coroutineScope.launch {
                            repository.deleteMovie(movie.id)
                            favoriteMovies = repository.getSavedMovies()
                        }
                    }) {
                        Text("Usuń z ulubionych")
                    }
                }
            }
        }
    }
}

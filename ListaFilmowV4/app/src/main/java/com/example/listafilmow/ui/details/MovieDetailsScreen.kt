package com.example.listafilmow.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.listafilmow.data.model.MovieEntity
import com.example.listafilmow.data.remote.repository.MovieRepository
import kotlinx.coroutines.launch

@Composable
fun MovieDetailsScreen(movie: MovieEntity, repository: MovieRepository) {
    val coroutineScope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
            contentDescription = "Movie Poster",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movie.title, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.overview, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                if (isFavorite) {
                    repository.deleteMovie(movie.id)
                } else {
                    repository.saveMovie(movie)
                }
                isFavorite = !isFavorite
            }
        }) {
            Text(text = if (isFavorite) "Usuń z ulubionych" else "Dodaj do ulubionych")
        }
    }
}

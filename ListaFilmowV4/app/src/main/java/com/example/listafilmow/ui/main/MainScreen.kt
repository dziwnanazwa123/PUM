package com.example.listafilmow.ui.main

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.listafilmow.viewmodel.MovieViewModel

@Composable
fun MainScreen() {
    val context = LocalContext.current.applicationContext as Application
    val movieViewModel: MovieViewModel = viewModel(factory = ViewModelProvider.AndroidViewModelFactory(context))

    var query by remember { mutableStateOf("") }
    val movies by movieViewModel.movies.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    innerTextField()
                    Button(onClick = { movieViewModel.searchMovies("7dfd98ca18588915a89406fd82a7f066", query) }) {
                        Text("Szukaj")
                    }
                }
            }
        )

        movies.forEach { movie ->
            Row(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = movie.title, color = Color.Black)
                    Text(text = movie.overview, color = Color.Gray)
                }
            }
        }
    }
}

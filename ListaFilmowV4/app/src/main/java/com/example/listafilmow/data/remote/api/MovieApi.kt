package com.example.listafilmow.data.remote.api

import com.example.listafilmow.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse
}

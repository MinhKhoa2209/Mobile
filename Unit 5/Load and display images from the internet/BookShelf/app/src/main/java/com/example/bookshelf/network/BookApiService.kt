package com.example.bookshelf.network

import com.example.bookshelf.model.BookResponse
import retrofit2.http.GET

interface BookApiService {
    @GET("books")
    suspend fun getBooks(): BookResponse
}

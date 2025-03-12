package com.example.bookshelf.data.network


import retrofit2.http.GET
import retrofit2.http.Query
import com.example.bookshelf.data.model.BookResponse

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookResponse
}
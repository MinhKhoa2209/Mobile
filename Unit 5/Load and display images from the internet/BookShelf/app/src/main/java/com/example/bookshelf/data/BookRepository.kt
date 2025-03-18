package com.example.bookshelf.data

import android.util.Log
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BookResponse
import com.example.bookshelf.network.BookApiService

interface BookRepository {
    suspend fun getBooks(): List<BookItem>
}
class DefaultBookRepository(
    private val bookApiService: BookApiService) : BookRepository {
    override suspend fun getBooks(): List<BookItem> {
        val response: BookResponse = bookApiService.getBooks() // Nhận object
        Log.d("API Response", response.books.toString()) // In danh sách sách từ object
        return response.books // Trả về danh sách books
    }
}
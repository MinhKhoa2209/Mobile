package com.example.bookshelf.data.reposity

import com.example.bookshelf.data.network.BooksApiService
import com.example.bookshelf.data.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepository @Inject constructor(private val api: BooksApiService) {
    fun getBooks(query: String): Flow<List<Book>> = flow {
        emit(api.getBooks(query).items)
    }
}

package com.example.bookshelf.data

import android.util.Log
import com.example.bookshelf.network.BookApiService
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BookResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

interface BookRepository {
    suspend fun getBooks(query: String): List<BookItem>
}

class DefaultBookRepository(
    private val bookApiService: BookApiService) : BookRepository {
    override suspend fun getBooks(query: String): List<BookItem> {
        val response: BookResponse = bookApiService.getBooks(query)
        return response.items?.mapNotNull { bookItem ->
            bookItem.volumeInfo?.let { volumeInfo ->
                BookItem(
                    volumeInfo = VolumeInfo(
                        title = volumeInfo.title ?: "No Title",
                        authors = volumeInfo.authors ?: listOf("Unknown Author"),
                        publishedDate = volumeInfo.publishedDate ?: "Unknown Publish Date",
                        imageLinks = ImageLinks(thumbnail = volumeInfo.imageLinks?.thumbnail ?: ""
                        )
                    )
                )
            }
        } ?: emptyList()
    }
}

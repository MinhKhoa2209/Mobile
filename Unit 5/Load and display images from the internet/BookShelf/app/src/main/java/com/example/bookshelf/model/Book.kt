package com.example.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val books: List<BookItem>
)

@Serializable
data class BookItem(
    val title: String,
    val authors: List<String>? = null,
    @SerialName("img_src") val imgSrc: String
)

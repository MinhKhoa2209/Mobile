package com.example.bookshelf.data.model

data class BookResponse(
    @SerializedName("items") val items: List<Book>
)

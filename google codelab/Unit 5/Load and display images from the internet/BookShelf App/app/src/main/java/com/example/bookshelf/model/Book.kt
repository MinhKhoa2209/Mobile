package com.example.bookshelf.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    @Json(name = "items") val items: List<BookItem>? = emptyList()
)

@JsonClass(generateAdapter = true)
data class BookItem(
    @Json(name = "volumeInfo") val volumeInfo: VolumeInfo? = null
)

@JsonClass(generateAdapter = true)
data class VolumeInfo(
    @Json(name = "title") val title: String? = "Unknown Title",
    @Json(name = "authors") val authors: List<String>? = listOf("Unknown Author"),
    @Json(name = "publishedDate") val publishedDate: String? = "Unknown Date",
    @Json(name = "imageLinks") val imageLinks: ImageLinks? = null
)

@JsonClass(generateAdapter = true)
data class ImageLinks(
    @Json(name = "thumbnail") val thumbnail: String? = null
)

package com.example.bookshelf.data.model

data class VolumeInfo(
    @SerializedName("title") val title: String,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?
)
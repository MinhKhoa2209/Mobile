package com.example.bookshelf.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@SerializedName
data class ImageLinks(
    @SerializedName("thumbnail") val thumbnail: String
)
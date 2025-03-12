package com.example.bookshelf.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@SerializedName
data class Book(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
)
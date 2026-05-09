package com.pjasoft.mmorenomusicapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String? = null,
    val image: String
)
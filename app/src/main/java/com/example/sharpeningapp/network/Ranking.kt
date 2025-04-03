package com.example.sharpeningapp.network

import kotlinx.serialization.Serializable

@Serializable
data class Ranking(
    val name: String,
    val score: Int,
    val rank: Int,
)
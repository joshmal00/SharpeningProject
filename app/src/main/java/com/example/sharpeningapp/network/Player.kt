package com.example.sharpeningapp.network

import android.app.Activity
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val skills: List<Skill>,
    val activities: List<com.example.sharpeningapp.network.Activity>,
)

@Serializable
data class Skill(
    val id: Int,
    val name: String,
    val rank: Int,
    val level: Int,
    val xp: Int,
)

@Serializable
data class Activity(
    val id: Int,
    val name: String,
    val rank: Int,
    val score: Int,
)

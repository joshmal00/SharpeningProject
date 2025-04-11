package com.example.sharpeningapp.data

import com.example.sharpeningapp.network.Player

sealed interface PlayerResult {
    data class Success(val player: Player) : PlayerResult
    data class ApiError(val code: Int, val message: String) : PlayerResult
    data class NetworkError(val message: String) : PlayerResult
}
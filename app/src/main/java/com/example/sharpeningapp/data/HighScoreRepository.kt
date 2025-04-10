package com.example.sharpeningapp.data

import android.util.Log
import com.example.sharpeningapp.network.HighScoreApiService
import com.example.sharpeningapp.network.Player
import com.example.sharpeningapp.network.Ranking
import okio.IOException

class HighScoreRepository(
    private val service: HighScoreApiService
) {
    suspend fun getLeaders(table: Int, category: Int, size: Int): List<Ranking>? {
        try {
            val response = service.getLeaders(table, category, size)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e("API_ERROR", "Error retrieving leaderboard: ${response.code()}")
                return null
            }
        } catch (e: IOException) {
            Log.e("NETWORK_ERROR", "Network Error: ${e.message}")
            return null
        }
    }

    suspend fun getPlayerScores(username: String): Player? {
        try {
            val response = service.getPlayerScores(username)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e("API_ERROR", "Error retrieving player information: ${response.code()}")
                return null
            }
        } catch (e: IOException) {
            Log.e("NETWORK_ERROR", "Network Error: ${e.message}")
            return null
        }
    }
}
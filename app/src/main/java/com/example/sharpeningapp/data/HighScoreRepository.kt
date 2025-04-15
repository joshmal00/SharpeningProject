package com.example.sharpeningapp.data

import android.util.Log
import com.example.sharpeningapp.network.HighScoreApiService
import com.example.sharpeningapp.network.Ranking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okio.IOException

class HighScoreRepository(
    private val dao: LeadersDao,
    private val service: HighScoreApiService
) {

    val top50: Flow<List<LeaderEntity>> = dao.getLeaders()

    suspend fun updateLeaders() {
        val result = getLeaders(0,0,50)
        if (result != null) {
            val leaderEntities = result.map {
                LeaderEntity(
                    it.name,
                    it.rank,
                    it.score
                )
            }
            CoroutineScope(Dispatchers.IO).launch {
                dao.clearAll()
                dao.insertAll(leaderEntities)
            }
        } else {
            throw Exception("Failed to refresh leaders")
        }
    }

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

    suspend fun getPlayerScores(username: String): PlayerResult {
        try {
            val response = service.getPlayerScores(username)
            if (response.isSuccessful) {
                val player = response.body()
                if (player != null) {
                    return PlayerResult.Success(player)
                } else {
                    return PlayerResult.ApiError(response.code(), "Player not found")
                }
            } else {
                return PlayerResult.ApiError(response.code(),  "Player not found")
            }
        } catch (e: IOException) {
            return PlayerResult.NetworkError(
                if (e.message == null) "Network Error" else "Network Error: ${e.message}"
            )
        }
    }
}
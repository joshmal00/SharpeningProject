package com.example.sharpeningapp.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://secure.runescape.com/m=hiscore_oldschool/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface HighScoreApiService {
    @GET("ranking.json")
    suspend fun getLeaders(
        @Query("table") table: Int,
        @Query("category") category: Int,
        @Query("size") size: Int,
    ) : Response<List<Ranking>>

    @GET("index_lite.json")
    suspend fun getPlayerScores(@Query("player") player: String): Response<Player>
}

object HighScoreApi {
    val retrofitService: HighScoreApiService by lazy {
        retrofit.create(HighScoreApiService::class.java)
    }
}

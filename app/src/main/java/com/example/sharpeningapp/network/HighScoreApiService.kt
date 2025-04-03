package com.example.sharpeningapp.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://secure.runescape.com/m=hiscore_oldschool/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .build()

interface HighScoreApiService {
    @GET("ranking.json?table={skillId}&category={categoryId}&size={dataSize}")
    suspend fun getLeaders(
        @Path("skillId") skillId: Int,
        @Path("categoryId") categoryId: Int,
        @Path("dataSize") dataSize: Int,
    ) : Response<List<Ranking>>

    @GET("index_lite.json?player={username}")
    suspend fun getPlayerScores(@Path("username") username: String): Response<Player>
}

object HighScoreApi {
    val retrofitService: HighScoreApiService by lazy {
        retrofit.create(HighScoreApiService::class.java)
    }
}

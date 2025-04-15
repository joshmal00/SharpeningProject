package com.example.sharpeningapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sharpeningapp.network.Ranking

@Entity(tableName = "leaders")
data class LeaderEntity (
    @PrimaryKey
    var name: String,
    var rank: String,
    var score: String,
)

fun LeaderEntity.toRanking(): Ranking {
    return Ranking(
        name = name,
        rank = rank,
        score = score
    )
}
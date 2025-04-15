package com.example.sharpeningapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeadersDao {
    @Query(
        """
            SELECT *
            FROM leaders
            ORDER BY rank ASC
        """
    )
    fun getLeaders(): Flow<List<LeaderEntity>>

    @Insert
    suspend fun insertAll(leaders: List<LeaderEntity>)

    @Query(
        """
            DELETE
            FROM leaders
        """
    )
    fun clearAll()
}
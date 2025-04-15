package com.example.sharpeningapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LeaderEntity::class], version = 1)
abstract class LeadersDatabase : RoomDatabase() {
    abstract fun leadersDao(): LeadersDao
}
package com.example.roomexample.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nameEntity: NameEntity)

    @Delete
    suspend fun delete(nameEntity: NameEntity)

    @Query("SELECT * FROM NameEntity")
    fun getAll(): Flow<List<NameEntity>>
}
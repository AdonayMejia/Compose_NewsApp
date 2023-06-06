package com.example.compose_newsapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: NewsEntity)

    @Query("DELETE FROM FavoriteNews WHERE itemId = :id")
    suspend fun deleteItem(id: String)

    @Query("SELECT * FROM FavoriteNews")
    fun getFavoriteNews(): Flow<List<NewsEntity>>
}
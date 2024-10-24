package com.example.weather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM forecast_table")
    fun getFavorites(): List<ForecastModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(forecast: ForecastModel)

    @Query("DELETE FROM forecast_table WHERE timezone = :timezone")
    suspend fun deleteFavorite(timezone:String)

    @Query("DELETE FROM forecast_table")
    suspend fun deleteFavorites()
}
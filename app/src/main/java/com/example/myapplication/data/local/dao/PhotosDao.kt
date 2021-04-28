package com.example.myapplication.data.local.dao

import androidx.room.*
import com.example.myapplication.data.local.entity.ApiPhotos

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos")
    suspend fun getAll(): List<ApiPhotos>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(photos: List<ApiPhotos>)

    @Delete
    suspend fun delete(photos: ApiPhotos)

}
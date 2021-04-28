package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.PhotosDao
import com.example.myapplication.data.local.entity.ApiPhotos

@Database(entities = [ApiPhotos::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photosDao(): PhotosDao

}
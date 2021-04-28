package com.example.myapplication.data.local

import com.example.myapplication.data.local.entity.ApiPhotos

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getPhotos(): List<ApiPhotos> = appDatabase.photosDao().getAll()

    override suspend fun insertAll(photos: List<ApiPhotos>) =
        appDatabase.photosDao().insertAll(photos)

}
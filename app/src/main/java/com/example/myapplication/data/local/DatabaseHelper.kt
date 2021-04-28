package com.example.myapplication.data.local

import com.example.myapplication.data.local.entity.ApiPhotos

interface DatabaseHelper {

    suspend fun getPhotos(): List<ApiPhotos>

    suspend fun insertAll(photos: List<ApiPhotos>)

}
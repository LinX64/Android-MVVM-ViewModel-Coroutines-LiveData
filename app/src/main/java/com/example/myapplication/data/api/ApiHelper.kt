package com.example.myapplication.data.api

import com.example.myapplication.data.local.entity.ApiPhotos


interface ApiHelper {

    suspend fun getPhotos(): List<ApiPhotos>

}
package com.example.myapplication.data.api

import com.example.myapplication.data.local.entity.ApiPhotos
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<ApiPhotos>


}
package com.example.myapplication.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPhotos() = apiService.getPhotos()

}
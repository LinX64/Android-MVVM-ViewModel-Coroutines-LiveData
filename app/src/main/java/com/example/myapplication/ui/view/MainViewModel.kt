package com.example.myapplication.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.api.ApiHelper
import com.example.myapplication.data.local.DatabaseHelper
import com.example.myapplication.data.local.entity.ApiPhotos
import com.example.myapplication.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) :
    ViewModel() {

    private val photos = MutableLiveData<Resource<List<ApiPhotos>>>()

    init {
        fetchMyPhotos()
    }

    private fun fetchMyPhotos() {

        viewModelScope.launch {
            photos.postValue(Resource.loading(null))

            try {
                val photosFromDb = dbHelper.getPhotos()
                if (photosFromDb.isEmpty()) {

                    val photosFromApi = apiHelper.getPhotos()
                    val photosToInsertInDB = mutableListOf<ApiPhotos>()

                    for (apiPhotos in photosFromApi) {
                        val photos = ApiPhotos(
                            apiPhotos.id,
                            apiPhotos.title,
                            apiPhotos.url,
                            apiPhotos.albumId,
                            apiPhotos.thumbnailUrl
                        )
                        photosToInsertInDB.add(photos)
                    }

                    dbHelper.insertAll(photosToInsertInDB)
                    photos.postValue(Resource.success(photosToInsertInDB))

                } else {
                    photos.postValue(Resource.success(photosFromDb))
                }


            } catch (e: Exception) {
                photos.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getPhotos(): LiveData<Resource<List<ApiPhotos>>> {
        return photos
    }

}
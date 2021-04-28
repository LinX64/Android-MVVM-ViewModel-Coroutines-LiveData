package com.example.myapplication.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

class BindingAdapter {

    companion object {

        //load image from url using Binding Adapter

        @BindingAdapter("loadFromURL")
        @JvmStatic
        fun loadFromURL(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }

        }

    }
}

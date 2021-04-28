package com.example.myapplication.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.local.entity.ApiPhotos
import com.example.myapplication.databinding.ItemLayoutBinding

class MyAdapter(
    private val photos: ArrayList<ApiPhotos>
) : RecyclerView.Adapter<MyAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myItem: ApiPhotos) {
            binding.myItem = myItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(photos[position])

    fun addData(list: List<ApiPhotos>) {
        photos.addAll(list)

    }

}
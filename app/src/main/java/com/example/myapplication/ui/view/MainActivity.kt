package com.example.myapplication.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.util.Status.*
import com.example.myapplication.util.ViewModelFactory
import com.example.myapplication.data.api.ApiHelperImpl
import com.example.myapplication.data.api.RetrofitBuilder
import com.example.myapplication.data.local.DatabaseBuilder
import com.example.myapplication.data.local.DatabaseHelperImpl
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.base.MyAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter =
            MyAdapter(
                arrayListOf()
            )
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(MainViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getPhotos().observe(this, {
            when (it.status) {
                SUCCESS -> {
                    binding.progressBar.visibility = View.GONE

                    it.data?.let { photos -> adapter.addData(photos) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}

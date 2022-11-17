package com.dialogy.studio.shoplistv2.home.categorydetail.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dialogy.studio.shoplistv2.databinding.CategoryDetailActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: CategoryDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoryDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
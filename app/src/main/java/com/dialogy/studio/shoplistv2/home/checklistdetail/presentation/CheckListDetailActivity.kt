package com.dialogy.studio.shoplistv2.home.checklistdetail.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dialogy.studio.shoplistv2.databinding.CheckListDetailActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckListDetailActivity : AppCompatActivity() {
    private lateinit var binding: CheckListDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CheckListDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
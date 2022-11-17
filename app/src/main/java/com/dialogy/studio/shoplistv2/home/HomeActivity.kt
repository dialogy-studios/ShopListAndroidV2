package com.dialogy.studio.shoplistv2.home

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dialogy.studio.shoplistv2.databinding.HomeActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {}

    override fun onStart() {
        super.onStart()
        setupNavigationController()
    }

    private fun setupNavigationController() {
        binding.bottomNavigation.setupWithNavController(binding.navHostFragment.findNavController())
    }

}
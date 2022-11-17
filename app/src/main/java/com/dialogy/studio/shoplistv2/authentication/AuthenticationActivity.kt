package com.dialogy.studio.shoplistv2.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dialogy.studio.shoplistv2.databinding.AuthenticationActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: AuthenticationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthenticationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
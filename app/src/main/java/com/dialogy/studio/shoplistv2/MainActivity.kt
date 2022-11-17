package com.dialogy.studio.shoplistv2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.dialogy.studio.shoplistv2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        startActivity(
            Intent().apply {
                data = AUTHENTICATION_DEEPLINK.toUri()
            }
        )

        finish()

    }

}

const val AUTHENTICATION_DEEPLINK = "shoplist://authentication"
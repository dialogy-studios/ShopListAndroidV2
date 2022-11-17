package com.dialogy.studio.shoplistv2.authentication.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.dialogy.studio.shoplistv2.databinding.RegisterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var binding: RegisterFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater)
        setup()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setup() {
        setupListeners()
    }

    private fun setupListeners() {
        binding?.finish?.setOnClickListener {
            startActivity(
                Intent().apply {
                    data = HOME_DEEPLINK.toUri()
                }
            )
        }
    }
}

private const val HOME_DEEPLINK = "shoplist://home"
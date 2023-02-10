package com.dialogy.studio.shoplistv2.authentication.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dialogy.studio.shoplistv2.databinding.LoginFragmentBinding
import com.dialogy.studio.shoplistv2.databinding.LoginNormalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var binding: LoginFragmentBinding? = null
        set(value) {
            field = value
            if (value != null) {
                normalState = value.normal
            }
        }
    private var normalState: LoginNormalBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)
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
        normalState?.registerBtn?.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }
}
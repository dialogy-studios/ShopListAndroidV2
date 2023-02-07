package com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.dialogy.studio.shoplistv2.databinding.RegisterConfirmBinding
import com.dialogy.studio.shoplistv2.databinding.RegisterConfirmNormalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterConfirmFragment : Fragment() {
    private var binding: RegisterConfirmBinding? = null
        set(value) {
            if (value != null) {
                normalState = value.normal
            }
            field = value
        }
    private var normalState: RegisterConfirmNormalBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterConfirmBinding.inflate(inflater)
        setup()
        return binding?.root
    }

    private fun setup() {
        setupListeners()
    }

    private fun setupListeners() {
        normalState?.apply {
            pinView.doOnTextChanged { text, start, before, count ->
                
            }
        }
    }


}
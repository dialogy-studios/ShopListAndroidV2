package com.dialogy.studio.shoplistv2.authentication.register.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dialogy.studio.shoplistv2.R
import com.dialogy.studio.shoplistv2.authentication.register.presentation.model.UserRegistrationInput
import com.dialogy.studio.shoplistv2.constants.DeeplinkConstants
import com.dialogy.studio.shoplistv2.databinding.RegisterFragmentBinding
import com.dialogy.studio.shoplistv2.strings.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var binding: RegisterFragmentBinding? = null
    private val vm by viewModels<RegisterViewModel>()
    private var registrationPayload = UserRegistrationInput()
        set(value) {
            binding?.continueBtn?.isEnabled = value.areAllFieldsValid()
            field = value
        }

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
        binding?.name?.doOnTextChanged { text, start, before, count ->
            registrationPayload = registrationPayload.copy(
                name= text?.toString().orEmpty()
            )
        }

        binding?.lastname?.doOnTextChanged { text, start, before, count ->
            registrationPayload = registrationPayload.copy(
                lastname = text?.toString().orEmpty()
            )
        }

        binding?.email?.doOnTextChanged { text, start, before, count ->
            val email =  text?.toString().orEmpty().trim()
            registrationPayload = registrationPayload.copy(
                email = email
            )
            validateEmail(text?.trim() ?: "")
        }

        binding?.password?.doOnTextChanged { text, start, before, count ->
            val password = text?.toString().orEmpty().trim()
            registrationPayload = registrationPayload.copy(
                password = password
            )
            validatePassword(password)
        }

        binding?.passwordConfirm?.doOnTextChanged { text, start, before, count ->
            val password = text?.toString().orEmpty().trim()
            val passwordAreEqual = password == registrationPayload.password
            binding?.passwordConstraint5?.setTextColor(
                if (passwordAreEqual) {
                    Color.GREEN
                } else {
                    Color.RED
                }
            )
        }

        binding?.continueBtn?.setOnClickListener {
            vm.sendRegisterRequest(registrationPayload)
        }
    }

    private fun validatePassword(password: String) {

        binding?.passwordConstraint1?.setTextColor(
            if (password.containLowerCase().not() || password.containUpperCase().not()) {
                Color.RED
            } else {
                Color.GREEN
            }
        )

        binding?.passwordConstraint2?.setTextColor(
            if (password.containDigit().not()) {
                Color.RED
            } else {
                Color.GREEN
            }
        )

        binding?.passwordConstraint3?.setTextColor(
            if (password.containSymbol().not()) {
                Color.RED
            } else {
                Color.GREEN
            }
        )

        binding?.passwordConstraint4?.setTextColor(
            if (password.length < 8) {
                Color.RED
            } else {
                Color.GREEN
            }
        )
    }
    private fun validateEmail(email: CharSequence) {
        if (email.isValidEmail().not()) {
            binding?.emailLayout?.error = context?.getString(R.string.register_invalid_email)
        } else {
            binding?.emailLayout?.error = null
        }
    }
}
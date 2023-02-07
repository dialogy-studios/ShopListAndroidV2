package com.dialogy.studio.shoplistv2.authentication.register.userinput.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dialogy.studio.shoplistv2.R
import com.dialogy.studio.shoplistv2.authentication.register.userinput.domain.model.RegistrationThrowable
import com.dialogy.studio.shoplistv2.authentication.register.userinput.presentation.model.UserRegistrationInput
import com.dialogy.studio.shoplistv2.components.textview.PhoneNumberTextWatcher
import com.dialogy.studio.shoplistv2.constants.PhoneNumberSupport
import com.dialogy.studio.shoplistv2.databinding.RegisterFragmentBinding
import com.dialogy.studio.shoplistv2.databinding.RegisterFragmentNormalBinding
import com.dialogy.studio.shoplistv2.strings.extensions.formatToPhoneNumber
import com.dialogy.studio.shoplistv2.strings.extensions.isValidEmail
import com.dialogy.studio.shoplistv2.strings.extensions.containLowerCase
import com.dialogy.studio.shoplistv2.strings.extensions.containUpperCase
import com.dialogy.studio.shoplistv2.strings.extensions.containDigit
import com.dialogy.studio.shoplistv2.strings.extensions.isValidPhoneNumber
import com.dialogy.studio.shoplistv2.strings.extensions.containSymbol
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    companion object {
        private const val NORMAL_STATE_LAYOUT = 0
        private const val LOADING_STATE_LAYOUT = 1
    }
    private var binding: RegisterFragmentBinding? = null
        set(value) {
            field = value
            if (value != null) {
                normalState = value.normalState
            }
        }
    private var normalState: RegisterFragmentNormalBinding? = null
    private val vm by viewModels<RegisterViewModel>()
    private var registrationPayload = UserRegistrationInput()
        set(value) {
            binding?.normalState?.continueBtn?.isEnabled = value.areAllFieldsValid()
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
        setupObservers()
    }

    private fun setupListeners() {
        normalState?.apply {
            name.doOnTextChanged { text, _, _, _ ->
                registrationPayload = registrationPayload.copy(
                    name= text?.toString().orEmpty()
                )
            }

            lastname.doOnTextChanged { text, _, _, _ ->
                registrationPayload = registrationPayload.copy(
                    lastname = text?.toString().orEmpty()
                )
            }

            email.doOnTextChanged { text, _, _, _ ->
                val email =  text?.toString().orEmpty().trim()
                registrationPayload = registrationPayload.copy(
                    username = email
                )
                validateEmail(text?.trim() ?: "")
            }

            phoneNumber.apply {
                addTextChangedListener(PhoneNumberTextWatcher(this, PhoneNumberSupport.PT_BR.pattern) { phoneNumber ->
                    registrationPayload = registrationPayload.copy(
                        phoneNumber = phoneNumber.formatToPhoneNumber()
                    )
                    validatePhoneNumber(phoneNumber)
                })
            }

            password.doOnTextChanged { text, _, _, _ ->
                val password = text?.toString().orEmpty().trim()
                registrationPayload = registrationPayload.copy(
                    password = password
                )
                validatePassword(password)
            }

            passwordConfirm.doOnTextChanged { text, _, _, _ ->
                val password = text?.toString().orEmpty().trim()
                val passwordAreEqual = password == registrationPayload.password
                passwordConstraint5.setTextColor(
                    if (passwordAreEqual) {
                        Color.GREEN
                    } else {
                        Color.RED
                    }
                )
            }

            continueBtn.setOnClickListener {
                vm.sendRegisterRequest(registrationPayload)
            }
        }
    }

    private fun setupObservers() {
        vm.state.observe(viewLifecycleOwner, handleNewState)
    }
    
    private val handleNewState: Observer<RegisterViewModel.RegisterViewModelState> = Observer { state ->
        when (state) {
            is RegisterViewModel.RegisterViewModelState.Register.Success -> handleRegisterSuccess()
            is RegisterViewModel.RegisterViewModelState.Register.Error -> handleRegisterError(state)
            is RegisterViewModel.RegisterViewModelState.Register.Loading -> handleRegisterLoading()
        }
    }

    private fun handleRegisterSuccess() {
        binding?.viewFlipper?.displayedChild = NORMAL_STATE_LAYOUT
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToRegisterConfirmFragment())
    }
    private fun handleRegisterError(state: RegisterViewModel.RegisterViewModelState.Register.Error) {
        binding?.viewFlipper?.displayedChild = NORMAL_STATE_LAYOUT
        val errorResourceId = when (state.error) {
            RegistrationThrowable.PhoneNumberConflict -> R.string.register_conflict_phone_number
            RegistrationThrowable.EmailInvalid, RegistrationThrowable.UsernameInvalid -> R.string.register_invalid_email
            RegistrationThrowable.InvalidParameterException -> R.string.register_invalid_parameter
            RegistrationThrowable.LastNameInvalid -> R.string.register_last_name_invalid
            RegistrationThrowable.PasswordInvalid -> R.string.register_password_invalid
            RegistrationThrowable.UsernameExistsException -> R.string.register_username_already_exists
            else -> R.string.register_unknown
        }
        val errorMessage = context?.getString(errorResourceId).orEmpty()
        binding?.root?.let {
            Snackbar.make(it, errorMessage, Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.RED)
                .setTextColor(Color.WHITE)
                .show()
        }
    }
    private fun handleRegisterLoading() {
        binding?.viewFlipper?.displayedChild = LOADING_STATE_LAYOUT
    }
    private fun validatePassword(password: String) {
        normalState?.apply {
            passwordConstraint1.setTextColor(
                if (password.containLowerCase().not() || password.containUpperCase().not()) {
                    Color.RED
                } else {
                    Color.GREEN
                }
            )

            passwordConstraint2.setTextColor(
                if (password.containDigit().not()) {
                    Color.RED
                } else {
                    Color.GREEN
                }
            )

            passwordConstraint3.setTextColor(
                if (password.containSymbol().not()) {
                    Color.RED
                } else {
                    Color.GREEN
                }
            )

            passwordConstraint4.setTextColor(
                if (password.length < 8) {
                    Color.RED
                } else {
                    Color.GREEN
                }
            )
        }
    }

    private fun validateEmail(email: CharSequence) {
        normalState?.apply {
            if (email.isValidEmail().not()) {
                emailLayout.error = context?.getString(R.string.register_invalid_email)
            } else {
                emailLayout.error = null
            }
        }
    }

    private fun validatePhoneNumber(phoneNumber: String) {
        normalState?.apply {
            if (phoneNumber.isValidPhoneNumber().not()) {
                phoneNumberLayout.error = context?.getString(R.string.register_invalid_phone_number)
            } else {
                phoneNumberLayout.error = null
            }
        }
    }
}
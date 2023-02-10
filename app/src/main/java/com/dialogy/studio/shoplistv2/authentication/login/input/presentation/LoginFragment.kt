package com.dialogy.studio.shoplistv2.authentication.login.input.presentation

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
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.model.LoginThrowable
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.model.UsernameInvalidException
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.model.PasswordInvalidException
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.model.UserNotFoundException
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.model.NotAuthorizedException
import com.dialogy.studio.shoplistv2.authentication.login.input.presentation.model.LoginInput
import com.dialogy.studio.shoplistv2.databinding.LoginFragmentBinding
import com.dialogy.studio.shoplistv2.databinding.LoginNormalBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    companion object {
        private const val NORMAL_STATE_LAYOUT = 0
        private const val LOADING_STATE_LAYOUT = 1
    }
    private val vm by viewModels<LoginViewModel>()
    private var binding: LoginFragmentBinding? = null
        set(value) {
            field = value
            if (value != null) {
                normalState = value.normal
            }
        }
    private var normalState: LoginNormalBinding? = null
    private var input: LoginInput = LoginInput()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
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

    private fun setupObservers() {
        vm.state.observe(viewLifecycleOwner, handleNewState)
    }

    private val handleNewState = Observer<LoginViewModel.State> { newState ->
        when (newState) {
            is LoginViewModel.State.Loading -> handleLoading()
            is LoginViewModel.State.Error -> handleError(newState.throwable)
            is LoginViewModel.State.Success -> handleSuccess()
            is LoginViewModel.State.Idle -> handleIdle()
        }
    }

    private fun handleLoading() {
        binding?.viewFlipper?.displayedChild = LOADING_STATE_LAYOUT
    }

    private fun handleError(throwable: LoginThrowable) {
        val msgResourceId = when (throwable.exception) {
            is UsernameInvalidException, is UserNotFoundException -> R.string.login_input_username_exception
            is PasswordInvalidException -> R.string.login_input_password_exception
            is NotAuthorizedException -> R.string.login_input_not_authorized
            else -> R.string.login_input_unknown
        }
        val msgString  = context?.getString(msgResourceId).orEmpty()
        binding?.let {
            Snackbar.make(it.root, msgString, Snackbar.LENGTH_LONG)
                .setTextColor(Color.WHITE)
                .setBackgroundTint(Color.RED)
                .show()
        }
    }

    private fun handleSuccess() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLoginConfirmFragment(input.username))
    }

    private fun handleIdle() {
        binding?.viewFlipper?.displayedChild = NORMAL_STATE_LAYOUT
    }

    private fun setupListeners() {
        normalState?.apply {
            usernameInput.doOnTextChanged { text, _, _, _ ->
                input = input.copy(
                    username = text.toString()
                )
            }

            passwordInput.doOnTextChanged { text, _, _, _ ->
                input = input.copy(
                    password = text.toString()
                )
            }

            registerBtn.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            loginBtn.setOnClickListener {
                vm.login(input)
            }
        }

    }
}
package com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dialogy.studio.shoplistv2.R
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.model.*
import com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation.model.LoginConfirmInput
import com.dialogy.studio.shoplistv2.constants.DeeplinkConstants
import com.dialogy.studio.shoplistv2.databinding.LoginConfirmFragmentBinding
import com.dialogy.studio.shoplistv2.databinding.LoginConfirmNormalBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginConfirmFragment : Fragment() {
    companion object {
        private const val CODE_LENGTH = 6
        private const val NORMAL_STATE_LAYOUT = 0
        private const val LOADING_STATE_LAYOUT = 1
    }

    private var binding: LoginConfirmFragmentBinding? = null
        set(value) {
            field = value
            if (value != null) {
                normalState = value.normal
            }
        }
    private var normalState: LoginConfirmNormalBinding? = null
    private val vm by viewModels<LoginConfirmViewModel>()
    private var input: LoginConfirmInput = LoginConfirmInput()
        set(value) {
            field = value
            if (input.confirmationCode.length == CODE_LENGTH) {
                vm.confirm(input)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginConfirmFragmentBinding.inflate(inflater, container, false)
        input = input.copy(
            username = arguments?.getString("username").orEmpty()
        )
        setup()
        return binding?.root
    }

    private fun setup() {
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        normalState?.apply {
            pinView.doOnTextChanged { text, _, _, _ ->
                input = input.copy(
                    confirmationCode = text.toString()
                )
            }
        }
    }

    private fun setupObservers() {
        vm.state.observe(viewLifecycleOwner, handleNewState)
    }

    private val handleNewState = Observer<LoginConfirmViewModel.State> { newState ->
        when (newState) {
            is LoginConfirmViewModel.State.Loading -> handleLoading()
            is LoginConfirmViewModel.State.Error -> handleError(newState.throwable)
            is LoginConfirmViewModel.State.Success -> handleSuccess()
            is LoginConfirmViewModel.State.Idle -> handleIdle()
        }
    }

    private fun handleLoading() {
        binding?.viewFlipper?.displayedChild = LOADING_STATE_LAYOUT
    }

    private fun handleError(throwable: LoginConfirmThrowable) {
        val msgErrorStringRes = when (throwable.exception) {
            is UsernameInvalidException -> R.string.login_confirm_username_invalid
            is ConfirmationCodeInvalidException, is CodeMismatchException  -> R.string.login_confirmation_code_invalid
            is ExpiredCodeException -> R.string.login_confirm_code_expired
            else -> R.string.login_confirm_unknown_error
        }
        val msgErrorString = context?.getString(msgErrorStringRes).orEmpty()
        binding?.apply {
            Snackbar.make(root, msgErrorString, Snackbar.LENGTH_LONG)
                .setTextColor(Color.WHITE)
                .setBackgroundTint(Color.RED)
                .show()
        }
    }

    private fun handleSuccess() {
        DeeplinkConstants.HOME.emit(requireContext())
        activity?.finish()
    }

    private fun handleIdle() {
        binding?.viewFlipper?.displayedChild = NORMAL_STATE_LAYOUT
        normalState?.pinView?.text = null
    }


}
package com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dialogy.studio.shoplistv2.R
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.model.CodeMismatchException
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.model.ConfirmationCodeInvalidException
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.model.ExpiredCodeException
import com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation.model.RegisterConfirmInput
import com.dialogy.studio.shoplistv2.databinding.RegisterConfirmBinding
import com.dialogy.studio.shoplistv2.databinding.RegisterConfirmNormalBinding
import com.dialogy.studio.shoplistv2.databinding.RegisterConfirmSuccessBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterConfirmFragment : Fragment() {
    companion object {
        private const val OTP_CODE_LENGTH = 6
        private const val NORMAL_STATE_LAYOUT = 0
        private const val LOADING_STATE_LAYOUT = 1
        private const val SUCCESS_STATE_LAYOUT = 2
    }

    private val vm by viewModels<RegisterConfirmViewModel>()
    private var payload = RegisterConfirmInput()
        set(value) {
            field = value
            if (value.confirmationCode.length == OTP_CODE_LENGTH) {
                vm.verifyCode(payload)
            }
        }

    private var binding: RegisterConfirmBinding? = null
        set(value) {
            if (value != null) {
                normalState = value.normal
                successState = value.success
            }
            field = value
        }
    private var normalState: RegisterConfirmNormalBinding? = null
    private var successState: RegisterConfirmSuccessBinding? = null

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
        setupPayload()
        setupObservers()
        setupOnBackPress()
    }

    private fun setupPayload() {
        payload = payload.copy(
            username = arguments?.getString("username").orEmpty()
        )
    }

    private fun setupObservers() {
        vm.state.observe(viewLifecycleOwner, handleNewState)
    }

    private val handleNewState: Observer<RegisterConfirmViewModel.State> = Observer { newState ->
        normalState?.pinView?.text = null
        when (newState) {
            is RegisterConfirmViewModel.State.Loading -> handleLoadingState()
            is RegisterConfirmViewModel.State.Success -> handleSuccessState()
            is RegisterConfirmViewModel.State.Error -> handleErrorState(newState)
            else -> {}
        }
    }

    private fun handleLoadingState() {
        binding?.viewFlipper?.displayedChild  = LOADING_STATE_LAYOUT
    }
    private fun handleSuccessState() {
        binding?.viewFlipper?.displayedChild  = SUCCESS_STATE_LAYOUT
    }

    private fun handleErrorState(state: RegisterConfirmViewModel.State.Error) {
        binding?.viewFlipper?.displayedChild  = NORMAL_STATE_LAYOUT
        binding?.root?.apply {
            val errorResourceId = when (state.throwable.exception) {
                is CodeMismatchException, is ConfirmationCodeInvalidException -> R.string.register_confirm_code_mismatch
                is ExpiredCodeException -> R.string.register_confirm_expired_code
                else -> R.string.register_unknown
            }
            val errorMessage = context.getString(errorResourceId)
            Snackbar.make(this, errorMessage, Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.RED)
                .setTextColor(Color.WHITE)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupOnBackPress() {
        val dispatcher = activity?.onBackPressedDispatcher
        val callback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding?.viewFlipper?.displayedChild == SUCCESS_STATE_LAYOUT) {
                    return
                }
                findNavController().popBackStack()
            }
        }

        findNavController().setLifecycleOwner(viewLifecycleOwner)

        dispatcher?.let {
            it.addCallback(callback)
            findNavController().setOnBackPressedDispatcher(it)

        }
    }
    private fun setupListeners() {
        normalState?.apply {
            pinView.doOnTextChanged { text, start, before, count ->
                payload = payload.copy(
                    confirmationCode = text.toString()
                )
            }
        }

        successState?.apply {
            ctaToLogin.setOnClickListener {
                findNavController().popBackStack(R.id.loginFragment, false)
            }
        }
    }

}
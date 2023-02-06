package com.dialogy.studio.shoplistv2.authentication.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.authentication.register.presentation.domain.RegisterInteractor
import com.dialogy.studio.shoplistv2.authentication.register.presentation.model.UserRegistrationInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerInteractor: RegisterInteractor
): ViewModel() {
    private val _state: MutableLiveData<RegisterViewModelState> = MutableLiveData()
    val state: LiveData<RegisterViewModelState> = _state
    private val _event: MutableLiveData<RegisterViewModelEvent> = MutableLiveData()
    val event: LiveData<RegisterViewModelEvent> = _event

    fun sendRegisterRequest(payload: UserRegistrationInput) {
        if (payload.areAllFieldsValid()) {
            registerInteractor.register(payload)
                .onStart { _state.value = RegisterViewModelState.Register.Loading }
                .mapLatest { _state.value = RegisterViewModelState.Register.Success }
                .catch { _state.value = RegisterViewModelState.Register.Error }
                .launchIn(viewModelScope)
        }
    }

    sealed class RegisterViewModelState {
        sealed class Register : RegisterViewModelState() {
            object Loading : Register()
            object Error : Register()
            object Success : Register()
        }
    }

    sealed class RegisterViewModelEvent {

    }

}


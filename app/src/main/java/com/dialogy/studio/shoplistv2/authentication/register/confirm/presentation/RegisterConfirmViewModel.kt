package com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.RegisterConfirmErrorMapper
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.RegisterConfirmInteractor
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.model.RegisterConfirmThrowable
import com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation.model.RegisterConfirmInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterConfirmViewModel @Inject constructor(
    private val interactor: RegisterConfirmInteractor,
    private val errorMapper: RegisterConfirmErrorMapper
): ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun verifyCode(payload: RegisterConfirmInput) {
        interactor.verify(payload)
            .onStart {
                _state.value = State.Loading
            }
            .mapLatest {
                _state.value = State.Success
            }
            .catch {
                val errorId = try {
                    it as HttpException
                } catch (error: Exception) {
                    null
                }?.response()?.errorBody()?.string().orEmpty()
                val registerConfirmThrowable = errorMapper.map(errorId)
                _state.value = State.Error(registerConfirmThrowable)
            }
            .launchIn(viewModelScope)
    }

    sealed class State {
        object Loading: State()
        object Success: State()
        data class Error(val throwable: RegisterConfirmThrowable): State()
    }
}
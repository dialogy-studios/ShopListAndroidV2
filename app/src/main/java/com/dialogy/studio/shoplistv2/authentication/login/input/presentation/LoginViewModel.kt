package com.dialogy.studio.shoplistv2.authentication.login.input.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.LoginErrorMapper
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.LoginInteractor
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.model.LoginThrowable
import com.dialogy.studio.shoplistv2.authentication.login.input.presentation.model.LoginInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val interactor: LoginInteractor,
    private val errorMapper: LoginErrorMapper
) : ViewModel() {
    private val _state = MutableLiveData<State>(State.Idle)
    val state: LiveData<State> = _state

    fun login(payload: LoginInput) {
        interactor.login(payload)
            .onStart {
                _state.value = State.Loading
            }
            .mapLatest {
                _state.value = State.Success
                _state.value = State.Idle
            }
            .catch {
                val errorId: String = try {
                    it as HttpException
                } catch (exception: Exception) {
                 null
                }?.response()?.errorBody()?.string().orEmpty()
                val throwable = errorMapper.map(errorId)
                _state.value = State.Error(throwable)
                _state.value = State.Idle
            }
            .launchIn(viewModelScope)
    }

    sealed class State {
        object Loading : State()
        object Success : State()
        object Idle : State()
        data class Error(val throwable: LoginThrowable) : State()
    }
}
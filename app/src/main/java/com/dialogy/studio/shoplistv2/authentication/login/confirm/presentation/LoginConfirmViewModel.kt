package com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.LoginConfirmErrorMapper
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.LoginConfirmInteractor
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.model.LoginConfirmThrowable
import com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation.model.LoginConfirmInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginConfirmViewModel @Inject constructor(
    private val interactor: LoginConfirmInteractor,
    private val errorMapper: LoginConfirmErrorMapper
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    fun confirm(input: LoginConfirmInput) {
        interactor.confirm(input)
            .onStart { _state.value = State.Loading }
            .mapLatest { authToken ->
                _state.value = State.Success(authToken)
                _state.value = State.Idle
            }
            .catch {
                val errorId = try {
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
        data class Error(val throwable: LoginConfirmThrowable) : State()
        data class Success(val authToken: String) : State()
        object Idle : State()
    }
}
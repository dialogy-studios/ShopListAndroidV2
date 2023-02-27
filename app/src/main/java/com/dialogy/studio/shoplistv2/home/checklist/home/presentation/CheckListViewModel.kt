package com.dialogy.studio.shoplistv2.home.checklist.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.home.checklist.home.domain.ICheckListInteractor
import com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.model.CheckListVO
import com.dialogy.studio.shoplistv2.network.di.model.ShopListDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CheckListViewModel @Inject constructor(
    private val interactor: ICheckListInteractor,
    private val dispatchers: ShopListDispatchers
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun fetchCheckList() {
        interactor
            .getCheckList()
            .onStart { _state.value = State.UI.Loading }
            .mapLatest {
                _state.value = State.UI.Success(it)
            }
            .catch {
                _state.value = State.UI.Error(it)
            }
            .launchIn(viewModelScope)
    }

    sealed class State {
        sealed class UI: State() {
            data class Success(val screenVO: List<CheckListVO>): UI()
            data class Error(val error: Throwable): UI()
            object Loading: UI()
        }
    }
}
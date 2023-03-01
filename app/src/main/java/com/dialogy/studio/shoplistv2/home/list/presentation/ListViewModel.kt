package com.dialogy.studio.shoplistv2.home.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.home.list.domain.IListInteractor
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.CategoryVerticalListVO
import com.dialogy.studio.shoplistv2.network.model.ShopListDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val interactor: IListInteractor,
    private val dispatchers: ShopListDispatchers
) : ViewModel() {
    private val _state = MutableLiveData<State>(State.UI.Idle)
    val state: LiveData<State> = _state

    fun fetchList() {
        viewModelScope.launch {
            try {
                _state.value = State.UI.Loading
                interactor
                    .fetchList()
                    .collect {
                        _state.value = State.UI.Success(it)
                    }
            } catch (error: Throwable) {
                _state.value = State.UI.Error(error)
            }
        }
    }

    sealed class State {
        sealed class UI: State() {
            object Idle: UI()
            object Loading: UI()
            data class Error(val error: Throwable): UI()
            data class Success(val data: List<CategoryVerticalListVO>): UI()
        }
    }
}
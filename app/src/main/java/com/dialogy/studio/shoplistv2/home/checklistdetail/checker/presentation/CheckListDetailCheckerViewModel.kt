package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.ICheckListDetailCheckerInteractor
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model.CheckListDetailCheckerVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CheckListDetailCheckerViewModel @Inject constructor(
    private val interactor: ICheckListDetailCheckerInteractor
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.UI.Loading)
    val state: LiveData<State> = _state

    fun fetchCheckListDetail(checkListDetailId: String) {
        interactor.fetchCheckList(checkListDetailId)
            .onStart {
                _state.value = State.UI.Loading
            }
            .mapLatest {
                _state.value = State.UI.Success(it)
            }
            .catch {
                _state.value = State.UI.Error(it)
            }
            .launchIn(viewModelScope)
    }


    sealed class State {
        sealed class UI : State() {
            data class Success(val vo: List<CheckListDetailCheckerVO>): UI()
            object Loading: UI()
            data class Error(val error: Throwable): UI()
        }
    }
}
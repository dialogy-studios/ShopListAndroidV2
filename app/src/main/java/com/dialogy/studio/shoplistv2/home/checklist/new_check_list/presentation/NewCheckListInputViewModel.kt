package com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.domain.NewCheckListInteractor
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation.model.NewCheckListInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NewCheckListInputViewModel @Inject constructor(
    private val interactor: NewCheckListInteractor
): ViewModel() {
    private val _state = MutableLiveData<State>(State.Idle)
    val state: LiveData<State> = _state

    fun save(input: NewCheckListInput) {
        interactor.save(input)
            .onStart { _state.value =  State.Loading }
            .mapLatest { _state.value = State.Success }
            .catch { _state.value = State.Error }
            .launchIn(viewModelScope)
    }


    sealed class State {
        object Idle : State()
        object Success : State()
        object Loading : State()
        object Error : State()
    }
}
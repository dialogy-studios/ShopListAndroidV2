package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.domain.ICategoryProductListInteractor
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model.CategoryProductListVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryProductListViewModel @Inject constructor(
    private val interactor: ICategoryProductListInteractor
) : ViewModel() {

    private val _state: MutableLiveData<CategoryProductListState> = MutableLiveData()
    val state: LiveData<CategoryProductListState> = _state

    fun fetchProductList(categoryId: String) {
        interactor.fetchProductList(categoryId)
            .onStart {
                _state.value = CategoryProductListState.UI.Loading
            }
            .mapLatest {
                _state.value = CategoryProductListState.UI.Success(it)
            }
            .catch { throwable ->
                CategoryProductListState.UI.Error(throwable)
            }
            .launchIn(viewModelScope)
    }

    sealed class CategoryProductListState {
        sealed class UI : CategoryProductListState() {
            data class Success(val vo: CategoryProductListVO): UI()
            object Loading : UI()
            data class Error(val vo: Any): UI()
        }
    }
}


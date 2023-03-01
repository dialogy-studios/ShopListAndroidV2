package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.domain

import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.ICategoryProductListRepository
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model.CategoryProductListVO
import com.dialogy.studio.shoplistv2.network.model.ShopListDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.await
import javax.inject.Inject

interface ICategoryProductListInteractor {
    fun fetchProductList(categoryId: String, page: Int) : Flow<CategoryProductListVO>
}

class CategoryProductListInteractor @Inject constructor (
    private val dispatchers: ShopListDispatchers,
    private val repository : ICategoryProductListRepository,
    private val categoryProductListMapper: ICategoryProductListMapper
) : ICategoryProductListInteractor {
    override fun fetchProductList(categoryId: String, page: Int): Flow<CategoryProductListVO> = flow {
        val data = repository.fetchProductList(categoryId, page).await()
        val vo = categoryProductListMapper.map(data)
        emit(vo)
    }.flowOn(dispatchers.io)

}
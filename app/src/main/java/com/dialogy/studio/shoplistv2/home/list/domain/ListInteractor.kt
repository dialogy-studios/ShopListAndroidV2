package com.dialogy.studio.shoplistv2.home.list.domain

import com.dialogy.studio.shoplistv2.home.list.data.IListRepository
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.IVerticalCategoryListMapper
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.CategoryVerticalListVO
import com.dialogy.studio.shoplistv2.network.model.ShopListDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject

interface IListInteractor {
    suspend fun fetchList(): Flow<List<CategoryVerticalListVO>>
}

class ListInteractor @Inject constructor(
    private val repository: IListRepository,
    private val dispatchers: ShopListDispatchers,
    private val verticalCategoryListMapper: IVerticalCategoryListMapper
) : IListInteractor {
    override suspend fun fetchList(): Flow<List<CategoryVerticalListVO>> = withContext(dispatchers.io) {
        flow {
            val response = repository.getList().await()
            val categoryListVO = verticalCategoryListMapper.map(response?.categoryList)
            emit(categoryListVO)
        }
    }

}
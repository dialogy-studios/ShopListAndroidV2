package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain

import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.ICheckListDetailCheckerRepository
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.mapper.ICheckListDetailCheckerMapper
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model.CheckListDetailCheckerVO
import com.dialogy.studio.shoplistv2.network.di.model.ShopListDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.await
import javax.inject.Inject

interface ICheckListDetailCheckerInteractor {
    fun fetchCheckList(checkListId: String): Flow<List<CheckListDetailCheckerVO>>
}

class CheckListDetailCheckerInteractor @Inject constructor(
    private val repository: ICheckListDetailCheckerRepository,
    private val mapper: ICheckListDetailCheckerMapper,
    private val dispatchers: ShopListDispatchers
) : ICheckListDetailCheckerInteractor {
    override fun fetchCheckList(checkListId: String): Flow<List<CheckListDetailCheckerVO>> =
        flow {
            val response = repository.fetchCheckList(checkListId).await()
            val vo = mapper.map(response)
            emit(vo)
        }
            .flowOn(dispatchers.io)
}
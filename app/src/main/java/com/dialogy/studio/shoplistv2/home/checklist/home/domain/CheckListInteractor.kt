package com.dialogy.studio.shoplistv2.home.checklist.home.domain

import com.dialogy.studio.shoplistv2.home.checklist.home.data.ICheckListRepository
import com.dialogy.studio.shoplistv2.home.checklist.home.domain.mapper.ICheckListComponentRVMapper
import com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.model.CheckListVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

interface ICheckListInteractor {
    fun getCheckList(): Flow<List<CheckListVO>>
}

class CheckListInteractor @Inject constructor(
    private val repository: ICheckListRepository,
    private val checkListComponentListRVMapper: ICheckListComponentRVMapper
) : ICheckListInteractor {
    override fun getCheckList(): Flow<List<CheckListVO>> =
        flow {
            val checkListResponse = repository.getCheckListRepository().await()
            val vo = checkListComponentListRVMapper.bind(checkListResponse)
            emit(vo)
        }
}
package com.dialogy.studio.shoplistv2.home.checklist.data

import com.dialogy.studio.shoplistv2.home.checklist.data.model.CheckListResponse
import retrofit2.Call
import javax.inject.Inject

interface ICheckListRepository {
    fun getCheckListRepository(): Call<List<CheckListResponse>>
}

class CheckListRepository @Inject constructor(
    private val service: ICheckListService
) : ICheckListRepository {
    override fun getCheckListRepository(): Call<List<CheckListResponse>> =
        service.getCheckList()

}
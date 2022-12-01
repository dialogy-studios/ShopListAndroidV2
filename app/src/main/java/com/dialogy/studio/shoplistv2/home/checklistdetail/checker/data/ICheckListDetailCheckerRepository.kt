package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data

import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.model.CheckListDetailCheckerResponse
import retrofit2.Call
import javax.inject.Inject

interface ICheckListDetailCheckerRepository {
    fun fetchCheckList(checkListId: String): Call<List<CheckListDetailCheckerResponse>>
}

class CheckListDetailCheckerRepository @Inject constructor(
    private val service: ICheckListDetailCheckerService
): ICheckListDetailCheckerRepository {
    override fun fetchCheckList(checkListId: String): Call<List<CheckListDetailCheckerResponse>> =
        service.fetchCheckList(checkListId)

}
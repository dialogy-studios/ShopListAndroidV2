package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data

import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.model.CheckListDetailCheckerResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ICheckListDetailCheckerService {
    @GET("/check-list-detail/{checkListId}")
    @MOCK("check-list-detail.json")
    fun fetchCheckList(
        @Path("checkListId")
        checkListId: String
    ): Call<List<CheckListDetailCheckerResponse>>
}
package com.dialogy.studio.shoplistv2.home.checklist.data

import com.dialogy.studio.shoplistv2.home.checklist.data.model.CheckListResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.Call
import retrofit2.http.GET

interface ICheckListService {
    @GET("check-list")
    @MOCK("fetch_check_list.json")
    fun getCheckList(): Call<List<CheckListResponse>>
}
package com.dialogy.studio.shoplistv2.home.checklist.new_check_list.data

import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation.model.NewCheckListInput
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NewCheckListService {
    @POST("/market-list")
    fun save(
        @Body
        input: NewCheckListInput): Call<String>
}
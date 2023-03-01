package com.dialogy.studio.shoplistv2.home.checklist.new_check_list.data

import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation.model.NewCheckListInput
import retrofit2.Call
import javax.inject.Inject

interface NewCheckListRepository {
    fun save(input: NewCheckListInput): Call<String>
}


class NewCheckListRepositoryImp @Inject constructor(
    private val service: NewCheckListService
): NewCheckListRepository {
    override fun save(input: NewCheckListInput): Call<String> =
        service.save(input)
}
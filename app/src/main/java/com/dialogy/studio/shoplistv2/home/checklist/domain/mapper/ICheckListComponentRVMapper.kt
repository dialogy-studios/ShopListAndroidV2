package com.dialogy.studio.shoplistv2.home.checklist.domain.mapper

import com.dialogy.studio.shoplistv2.home.checklist.data.model.CheckListResponse
import com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list.model.CheckListVO
import javax.inject.Inject

interface ICheckListComponentRVMapper {
    fun bind(input: List<CheckListResponse>): List<CheckListVO>
}

class CheckListComponentRVMapper @Inject constructor(): ICheckListComponentRVMapper{
    override fun bind(input: List<CheckListResponse>): List<CheckListVO> =
        input.map {
            CheckListVO(
                title= it.title.orEmpty(),
                selectedProductsShowCase= it.selectedProductsShowCase ?: listOf(),
                checkListTotal= it.checkListTotal.orEmpty()
            )
        }

}
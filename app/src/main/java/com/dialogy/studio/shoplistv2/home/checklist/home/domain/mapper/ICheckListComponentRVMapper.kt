package com.dialogy.studio.shoplistv2.home.checklist.home.domain.mapper

import com.dialogy.studio.shoplistv2.home.checklist.home.data.model.CheckListResponse
import com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.model.CheckListVO
import javax.inject.Inject

interface ICheckListComponentRVMapper {
    fun bind(input: List<CheckListResponse>): List<CheckListVO>
}

class CheckListComponentRVMapper @Inject constructor(): ICheckListComponentRVMapper {
    override fun bind(input: List<CheckListResponse>): List<CheckListVO> =
        input.map {
            CheckListVO(
                title= it.title.orEmpty(),
                selectedProductsShowCase= it.selectedProductsShowCase ?: listOf(),
                checkListTotal= it.checkListTotal.orEmpty()
            )
        }

}
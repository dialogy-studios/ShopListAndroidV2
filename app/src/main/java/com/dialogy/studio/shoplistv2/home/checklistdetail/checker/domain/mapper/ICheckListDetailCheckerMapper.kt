package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.mapper

import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.model.CheckListDetailCheckerResponse
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model.CheckListDetailCheckerVO
import javax.inject.Inject

interface ICheckListDetailCheckerMapper {
    fun map(input: List<CheckListDetailCheckerResponse>): List<CheckListDetailCheckerVO>
}

class CheckListDetailCheckerMapper @Inject constructor(): ICheckListDetailCheckerMapper {
    override fun map(input: List<CheckListDetailCheckerResponse>): List<CheckListDetailCheckerVO> =
        input.map {
            CheckListDetailCheckerVO(
                isChecked = it.isChecked ?: false,
                productName = it.productName.orEmpty(),
                productPrice = it.productPrice.orEmpty(),
                thumb = it.thumb.orEmpty()
            )
        }

}
package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.mapper

import com.dialogy.studio.shoplistv2.currency.domain.formatter.CurrencyFormatter
import com.dialogy.studio.shoplistv2.currency.domain.formatter.ICurrencyFormatter
import com.dialogy.studio.shoplistv2.currency.domain.mapper.ICurrencyMapper
import com.dialogy.studio.shoplistv2.currency.model.DEFAULT_CURRENCY_VO
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.model.CheckListDetailCheckerResponse
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model.CheckListDetailCheckerVO
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.totalprice.model.TotalPriceVO
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.model.CheckListDetailCheckerScreenVO
import javax.inject.Inject

interface ICheckListDetailCheckerMapper {
    fun map(input: List<CheckListDetailCheckerResponse>): CheckListDetailCheckerScreenVO
}

class CheckListDetailCheckerMapper @Inject constructor(
    private val currencyMapper: ICurrencyMapper,
    private val currencyFormatter: ICurrencyFormatter
): ICheckListDetailCheckerMapper {
    override fun map(input: List<CheckListDetailCheckerResponse>): CheckListDetailCheckerScreenVO =
        input.fold(
            CheckListDetailCheckerScreenVO(
                selectedProductsVO = listOf(),
                totalPriceVO = TotalPriceVO(price = DEFAULT_CURRENCY_VO)
            )
        ) { screenVO, response ->
            val currencyVO = currencyMapper.map(response.productPrice)
            val selectedProductVO = CheckListDetailCheckerVO(
                isChecked = response.isChecked ?: false,
                productName = response.productName.orEmpty(),
                productPrice = currencyFormatter.format(currencyVO),
                thumb = response.thumb.orEmpty()
            )
            screenVO.copy(
                selectedProductsVO = screenVO.selectedProductsVO.toMutableList()
                    .plus(selectedProductVO),
                totalPriceVO = screenVO.totalPriceVO.copy(
                    price = screenVO.totalPriceVO.price.copy(value = screenVO.totalPriceVO.price.value + currencyVO.value)
                )
            )
        }

}
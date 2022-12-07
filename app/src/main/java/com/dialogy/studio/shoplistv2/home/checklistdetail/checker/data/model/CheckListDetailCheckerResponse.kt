package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.model

import com.dialogy.studio.shoplistv2.currency.model.CurrencyResponse

data class CheckListDetailCheckerResponse(
    val isChecked: Boolean? = null,
    val productName: String? = null,
    val productPrice: CurrencyResponse? = null,
    val thumb: String? = null
)
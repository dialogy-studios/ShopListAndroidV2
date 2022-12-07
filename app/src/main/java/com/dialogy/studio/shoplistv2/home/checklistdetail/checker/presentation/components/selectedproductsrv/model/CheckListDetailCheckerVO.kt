package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model

import com.dialogy.studio.shoplistv2.currency.model.CurrencyVO

data class CheckListDetailCheckerVO(
    val isChecked: Boolean,
    val productName: String,
    val productPrice: String,
    val thumb: String
)
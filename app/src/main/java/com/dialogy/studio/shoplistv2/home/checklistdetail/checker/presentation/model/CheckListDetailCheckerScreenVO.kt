package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.model

import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model.CheckListDetailCheckerVO
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.totalprice.model.TotalPriceVO

data class CheckListDetailCheckerScreenVO(
    val selectedProductsVO: List<CheckListDetailCheckerVO>,
    val totalPriceVO: TotalPriceVO
)
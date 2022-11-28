package com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list.model

data class CheckListVO(
    val title: String,
    val selectedProductsShowCase: List<String>,
    val checkListTotal: String
)
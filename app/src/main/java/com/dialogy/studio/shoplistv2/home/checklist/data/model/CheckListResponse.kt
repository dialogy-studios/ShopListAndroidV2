package com.dialogy.studio.shoplistv2.home.checklist.data.model

data class CheckListResponse(
    val title: String? = null,
    val selectedProductsShowCase: List<String>? = listOf(),
    val checkListTotal: String? = null
)
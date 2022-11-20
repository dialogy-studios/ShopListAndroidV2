package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model

data class CategoryProductVO(
    val id: Int,
    val name: String,
    val thumb: String
) {
    companion object {
        const val UNKNOWN_ID = -1
    }
}
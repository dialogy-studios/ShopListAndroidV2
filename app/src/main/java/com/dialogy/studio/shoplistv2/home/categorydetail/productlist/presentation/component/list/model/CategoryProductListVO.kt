package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model

data class CategoryProductListVO (
    val id: Int,
    val productList: List<CategoryProductVO>
    ) {
    companion object {
        const val UNKNOWN_ID = -1
        val DEFAULT = CategoryProductListVO(
            id = UNKNOWN_ID,

            productList = listOf()
        )
    }
}
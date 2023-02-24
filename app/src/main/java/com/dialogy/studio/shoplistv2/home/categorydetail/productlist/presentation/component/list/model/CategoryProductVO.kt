package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model

import com.dialogy.studio.shoplistv2.listener.IButtonListener

data class CategoryProductVO(
    val id: String,
    val name: String,
    val thumb: String,
    val listener: ICategoryProductListener? = null
) {
    companion object {
        const val UNKNOWN_ID = "-1"
    }
}
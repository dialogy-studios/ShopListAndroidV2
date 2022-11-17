package com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model

import com.dialogy.studio.shoplistv2.home.list.presentation.component.horizontalproductlist.ProductListVO

data class CategoryVerticalListVO(
    val id: String,
    val name: String,
    val productList: List<ProductListVO>,
    val listener: ICategoryVerticalListListener
)

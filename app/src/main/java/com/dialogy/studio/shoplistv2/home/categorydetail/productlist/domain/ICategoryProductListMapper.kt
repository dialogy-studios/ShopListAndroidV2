package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.domain

import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.model.CategoryProductListResponse
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model.CategoryProductListVO
import javax.inject.Inject

interface ICategoryProductListMapper {
    fun map(input: CategoryProductListResponse?): CategoryProductListVO
}

class CategoryProductListMapper @Inject constructor(
    private val categoryProductMapper: ICategoryProductMapper
): ICategoryProductListMapper {
    override fun map(input: CategoryProductListResponse?): CategoryProductListVO =
        input?.let {
            CategoryProductListVO(
                id = input.id ?: CategoryProductListVO.UNKNOWN_ID,
                productList = categoryProductMapper.map(it.productList)
            )
        } ?: CategoryProductListVO.DEFAULT
}
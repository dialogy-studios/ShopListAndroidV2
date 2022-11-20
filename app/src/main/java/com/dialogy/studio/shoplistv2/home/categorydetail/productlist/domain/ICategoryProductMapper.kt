package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.domain

import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.model.CategoryProductResponse
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model.CategoryProductVO
import javax.inject.Inject

interface ICategoryProductMapper {
    fun map(input: List<CategoryProductResponse>?): List<CategoryProductVO>
}

class CategoryProductMapper @Inject constructor() : ICategoryProductMapper {
    override fun map(input: List<CategoryProductResponse>?): List<CategoryProductVO> =
        input?.map {
            CategoryProductVO(
                id = it.id ?: CategoryProductVO.UNKNOWN_ID,
                name = it.name.orEmpty(),
                thumb =  it.thumb.orEmpty()
            )
        } ?: listOf()

}
package com.dialogy.studio.shoplistv2.home.list.presentation.component.horizontalproductlist

import com.dialogy.studio.shoplistv2.home.model.ProductResponse
import javax.inject.Inject

interface IProductListVOMapper {
    fun map(input: List<ProductResponse?>?): List<ProductListVO>
}

class ProductListVOMapper @Inject constructor() : IProductListVOMapper {
    override fun map(input: List<ProductResponse?>?): List<ProductListVO> =
        input?.let {
            input.filterNotNull()
                .map {
                    ProductListVO(
                        thumb = it.thumb.orEmpty()
                    )
                }
        } ?: listOf()
}
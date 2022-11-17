package com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist

import com.dialogy.studio.shoplistv2.home.list.data.response.CategoryListResponse
import com.dialogy.studio.shoplistv2.home.list.presentation.component.horizontalproductlist.ProductListVOMapper
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.ICategoryVerticalListListener
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.CategoryVerticalListVO
import com.dialogy.studio.shoplistv2.listener.IButtonListener
import javax.inject.Inject

interface IVerticalCategoryListMapper {
    fun map(input: List<CategoryListResponse?>?): List<CategoryVerticalListVO>
}

class VerticalCategoryListMapper @Inject constructor(
    private val productListVOMapper: ProductListVOMapper
) : IVerticalCategoryListMapper{
    override fun map(input: List<CategoryListResponse?>?): List<CategoryVerticalListVO> =
        input?.let {
            it.filterNotNull()
                .map {
                    CategoryVerticalListVO(
                        id = it.id.orEmpty(),
                        name = it.name.orEmpty(),
                        productList = productListVOMapper.map(it.productList),
                        listener = object : ICategoryVerticalListListener {
                            override val seeMore: IButtonListener? = null
                        }
                    )
                }
        } ?: listOf()
}
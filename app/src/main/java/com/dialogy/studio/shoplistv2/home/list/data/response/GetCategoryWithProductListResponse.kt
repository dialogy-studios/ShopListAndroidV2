package com.dialogy.studio.shoplistv2.home.list.data.response

import com.dialogy.studio.shoplistv2.home.model.ProductResponse
import com.squareup.moshi.Json

data class GetCategoryWithProductListResponse(
    @Json(name = "category_list") val categoryList: List<CategoryListResponse>? = listOf()
)

data class CategoryListResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "product_list") val productList: List<ProductResponse>? = listOf()
)
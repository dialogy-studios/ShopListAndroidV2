package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.model

import com.squareup.moshi.Json

data class CategoryProductListResponse(
    @Json(name="id") val id: Int? = null,
    @Json(name="productList") val productList: List<CategoryProductResponse>? = listOf()
)
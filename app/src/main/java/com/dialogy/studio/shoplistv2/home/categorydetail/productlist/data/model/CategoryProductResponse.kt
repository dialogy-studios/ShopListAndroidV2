package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.model

import com.squareup.moshi.Json

data class CategoryProductResponse(
    @Json(name="id") val id: String? = null,
    @Json(name="name") val name: String? = null,
    @Json(name="thumb") val thumb: String? = null
)

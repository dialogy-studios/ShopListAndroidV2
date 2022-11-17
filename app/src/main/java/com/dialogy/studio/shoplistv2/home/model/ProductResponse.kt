package com.dialogy.studio.shoplistv2.home.model

import com.squareup.moshi.Json

data class ProductResponse(
    @Json(name = "name") val name: String? = null,
    @Json(name = "thumb") val thumb: String? = null
)
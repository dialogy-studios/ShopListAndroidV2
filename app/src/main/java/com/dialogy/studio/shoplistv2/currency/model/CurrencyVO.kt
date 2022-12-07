package com.dialogy.studio.shoplistv2.currency.model

data class CurrencyVO(
    val value: Int,
    val factor: Int
)

const val DEFAULT_VALUE = 0
const val DEFAULT_FACTOR = 1
val DEFAULT_CURRENCY_VO = CurrencyVO(
    value = DEFAULT_VALUE,
    factor = DEFAULT_FACTOR
)

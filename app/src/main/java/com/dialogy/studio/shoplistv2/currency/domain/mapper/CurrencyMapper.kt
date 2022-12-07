package com.dialogy.studio.shoplistv2.currency.domain.mapper

import com.dialogy.studio.shoplistv2.currency.model.*
import javax.inject.Inject

interface ICurrencyMapper {
    fun map(input: CurrencyResponse?): CurrencyVO
}

class CurrencyMapper @Inject constructor() : ICurrencyMapper {
    override fun map(input: CurrencyResponse?): CurrencyVO =
        input?.let {
            CurrencyVO(
                value = it.value ?: DEFAULT_VALUE,
                factor = it.factor ?: DEFAULT_FACTOR
            )
        } ?: DEFAULT_CURRENCY_VO
}

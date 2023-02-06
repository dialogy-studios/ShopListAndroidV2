package com.dialogy.studio.shoplistv2.currency.domain.formatter

import com.dialogy.studio.shoplistv2.currency.model.CurrencyVO
import javax.inject.Inject

interface ICurrencyFormatter {
    fun format(input: CurrencyVO): String
}

class CurrencyFormatter @Inject constructor(): ICurrencyFormatter {
    override fun format(input: CurrencyVO): String {
        val value = input.value / input.factor
        return "R$ $value"
    }

}
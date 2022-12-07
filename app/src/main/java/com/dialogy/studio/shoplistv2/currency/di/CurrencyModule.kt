package com.dialogy.studio.shoplistv2.currency.di

import com.dialogy.studio.shoplistv2.currency.domain.formatter.CurrencyFormatter
import com.dialogy.studio.shoplistv2.currency.domain.formatter.ICurrencyFormatter
import com.dialogy.studio.shoplistv2.currency.domain.mapper.CurrencyMapper
import com.dialogy.studio.shoplistv2.currency.domain.mapper.ICurrencyMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class, FragmentComponent::class)
@Module
abstract class CurrencyModule {
    @Binds
    abstract fun bindMapper(impl: CurrencyMapper): ICurrencyMapper

    @Binds
    abstract fun bindFormatter(impl: CurrencyFormatter): ICurrencyFormatter
}
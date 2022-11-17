package com.dialogy.studio.shoplistv2.home.list.presentation.component.horizontalproductlist

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HorizontalProductListModule {
    @Binds
    abstract fun bindMapper(mapper: ProductListVOMapper): IProductListVOMapper
}
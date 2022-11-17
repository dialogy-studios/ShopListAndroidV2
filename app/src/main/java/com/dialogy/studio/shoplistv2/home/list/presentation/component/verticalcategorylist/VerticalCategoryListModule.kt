package com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class VerticalCategoryListModule {
    @Binds
    abstract fun bindMapper(mapper: VerticalCategoryListMapper): IVerticalCategoryListMapper
}
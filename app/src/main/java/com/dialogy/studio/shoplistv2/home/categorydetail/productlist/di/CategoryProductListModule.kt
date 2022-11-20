package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.di

import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.CategoryProductListRepository
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.ICategoryProductListRepository
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.ICategoryProductListService
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.domain.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class CategoryProductListModule {
    @Binds
    abstract fun bindRepository(impl: CategoryProductListRepository): ICategoryProductListRepository

    @Binds
    abstract fun bindInteractor(impl: CategoryProductListInteractor): ICategoryProductListInteractor

    @Binds
    abstract fun bindCategoryProductListMapper(impl: CategoryProductListMapper): ICategoryProductListMapper

    @Binds
    abstract fun bindCategoryProductMapper(impl: CategoryProductMapper): ICategoryProductMapper

    companion object {
        @Provides
        fun provideService(
            retrofit: Retrofit
        ) = retrofit.create(ICategoryProductListService::class.java)
    }
}
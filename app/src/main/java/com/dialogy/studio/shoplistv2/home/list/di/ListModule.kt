package com.dialogy.studio.shoplistv2.home.list.di

import com.dialogy.studio.shoplistv2.home.list.data.IListRepository
import com.dialogy.studio.shoplistv2.home.list.data.ListRepository
import com.dialogy.studio.shoplistv2.home.list.data.ListService
import com.dialogy.studio.shoplistv2.home.list.domain.IListInteractor
import com.dialogy.studio.shoplistv2.home.list.domain.ListInteractor
import com.dialogy.studio.shoplistv2.network.di.RetrofitModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module(
    includes = [
        RetrofitModule::class
    ]
)
@InstallIn(ViewModelComponent::class)
abstract class ListModule {
    @Binds
    abstract fun bindRepository(impl: ListRepository): IListRepository

    @Binds
    abstract fun bindInteractor(impl: ListInteractor): IListInteractor

    companion object {
        @Provides
        fun bindService(
            retrofit: Retrofit
        ): ListService =
            retrofit.create(ListService::class.java)
    }
}




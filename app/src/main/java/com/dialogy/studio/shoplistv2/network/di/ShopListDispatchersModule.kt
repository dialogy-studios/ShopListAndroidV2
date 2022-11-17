package com.dialogy.studio.shoplistv2.network.di

import com.dialogy.studio.shoplistv2.network.di.model.ShopListDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class ShopListDispatchersModule {
    @Provides
    fun provideDispatchers() =
        ShopListDispatchers(
            io = Dispatchers.IO,
            main = Dispatchers.Main,
            default = Dispatchers.Default,
        )
}
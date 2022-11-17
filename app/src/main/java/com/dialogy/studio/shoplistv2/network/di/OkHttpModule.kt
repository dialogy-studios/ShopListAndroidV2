package com.dialogy.studio.shoplistv2.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.brunogabriel.mockpinterceptor.MockPInterceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient

@Module
@InstallIn(ViewModelComponent::class)
object OkHttpModule {
    @Provides
    fun providesOkHttp(
        @ApplicationContext
        context: Context
    ) : OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                MockPInterceptor
                    .Builder(context)
                    .addDelayInMillis(0, 1000L)
                    .build()
            )
            .build()
}
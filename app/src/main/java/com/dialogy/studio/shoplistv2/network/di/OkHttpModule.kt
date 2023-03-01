package com.dialogy.studio.shoplistv2.network.di

import android.content.Context
import com.dialogy.studio.shoplistv2.network.interceptors.OAuthInterceptor
import com.dialogy.studio.shoplistv2.network.interceptors.OAuthInterceptorImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.brunogabriel.mockpinterceptor.MockPInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient

@Module(
    includes = [
        InterceptorModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object OkHttpModule {
    @Provides
    fun providesOkHttp(
        @ApplicationContext
        context: Context,
        @OAuthInterceptorImp oauthInterceptor: Interceptor
    ) : OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(oauthInterceptor)
            .addInterceptor(
                MockPInterceptor
                    .Builder(context)
                    .addDelayInMillis(0, 1000L)
                    .build()
            )
            .build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class InterceptorModule {
    @Binds
    @OAuthInterceptorImp
    abstract fun bindOauthInterceptor(impl: OAuthInterceptor): Interceptor
}
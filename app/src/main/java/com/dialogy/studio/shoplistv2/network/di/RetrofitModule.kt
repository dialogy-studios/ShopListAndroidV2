package com.dialogy.studio.shoplistv2.network.di

import com.dialogy.studio.shoplistv2.network.di.model.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(
    includes = [
        OkHttpModule::class
    ]
)
@InstallIn(ActivityComponent::class)
class RetrofitModule {
    @Provides
    fun providesRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}

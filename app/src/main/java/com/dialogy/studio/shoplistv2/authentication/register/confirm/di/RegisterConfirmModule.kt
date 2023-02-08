package com.dialogy.studio.shoplistv2.authentication.register.confirm.di

import com.dialogy.studio.shoplistv2.authentication.register.confirm.data.RegisterConfirmRepository
import com.dialogy.studio.shoplistv2.authentication.register.confirm.data.RegisterConfirmRepositoryImp
import com.dialogy.studio.shoplistv2.authentication.register.confirm.data.RegisterConfirmService
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.RegisterConfirmErrorMapper
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.RegisterConfirmErrorMapperImp
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.RegisterConfirmInteractor
import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.RegisterConfirmInteractorImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
abstract class RegisterConfirmModule {
    @Binds
    abstract fun bindRepository(impl: RegisterConfirmRepositoryImp): RegisterConfirmRepository

    @Binds
    abstract fun bindInteractor(impl: RegisterConfirmInteractorImp): RegisterConfirmInteractor

    @Binds
    abstract fun bindMapper(impl: RegisterConfirmErrorMapperImp): RegisterConfirmErrorMapper

    companion object {
        @Provides
        fun provideService(
            retrofit: Retrofit
        ): RegisterConfirmService = retrofit.create(RegisterConfirmService::class.java)
    }
}
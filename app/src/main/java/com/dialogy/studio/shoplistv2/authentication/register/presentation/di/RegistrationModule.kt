package com.dialogy.studio.shoplistv2.authentication.register.presentation.di

import com.dialogy.studio.shoplistv2.authentication.register.presentation.data.RegisterRepository
import com.dialogy.studio.shoplistv2.authentication.register.presentation.data.RegisterRepositoryImp
import com.dialogy.studio.shoplistv2.authentication.register.presentation.data.RegisterService
import com.dialogy.studio.shoplistv2.authentication.register.presentation.domain.RegisterErrorMapper
import com.dialogy.studio.shoplistv2.authentication.register.presentation.domain.RegisterErrorMapperImp
import com.dialogy.studio.shoplistv2.authentication.register.presentation.domain.RegisterInteractor
import com.dialogy.studio.shoplistv2.authentication.register.presentation.domain.RegisterInteractorImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
abstract class RegistrationModule {
    @Binds
    abstract fun bindRepository(impl: RegisterRepositoryImp): RegisterRepository

    @Binds
    abstract fun bindInteractor(impl: RegisterInteractorImp): RegisterInteractor

    @Binds
    abstract fun bindErrorMapper(impl: RegisterErrorMapperImp): RegisterErrorMapper

    companion object {
        @Provides
        fun provideService(
            retrofit: Retrofit
        ): RegisterService = retrofit.create(RegisterService::class.java)
    }
}
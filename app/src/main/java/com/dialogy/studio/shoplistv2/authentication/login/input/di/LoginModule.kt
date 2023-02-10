package com.dialogy.studio.shoplistv2.authentication.login.input.di

import com.dialogy.studio.shoplistv2.authentication.login.input.data.LoginRepository
import com.dialogy.studio.shoplistv2.authentication.login.input.data.LoginRepositoryImp
import com.dialogy.studio.shoplistv2.authentication.login.input.data.LoginService
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.LoginErrorMapper
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.LoginErrorMapperImp
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.LoginInteractor
import com.dialogy.studio.shoplistv2.authentication.login.input.domain.LoginInteractorImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
abstract class LoginModule {
    @Binds
    abstract fun bindsErrorMapper(impl: LoginErrorMapperImp): LoginErrorMapper

    @Binds
    abstract fun bindRepository(impl: LoginRepositoryImp): LoginRepository

    @Binds
    abstract fun interactor(impl: LoginInteractorImp): LoginInteractor

    companion object {
        @Provides
        fun provideService(
            retrofit: Retrofit
        ): LoginService = retrofit.create(LoginService::class.java)
    }
}
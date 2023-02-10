package com.dialogy.studio.shoplistv2.authentication.login.confirm.di

import com.dialogy.studio.shoplistv2.authentication.login.confirm.data.LoginConfirmRepository
import com.dialogy.studio.shoplistv2.authentication.login.confirm.data.LoginConfirmRepositoryImp
import com.dialogy.studio.shoplistv2.authentication.login.confirm.data.LoginConfirmService
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.LoginConfirmErrorMapper
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.LoginConfirmErrorMapperImp
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.LoginConfirmInteractor
import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.LoginConfirmInteractorImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
abstract class LoginConfirmModule {
    @Binds
    abstract fun bindErrorMapper(impl: LoginConfirmErrorMapperImp): LoginConfirmErrorMapper

    @Binds
    abstract fun bindInteractor(impl: LoginConfirmInteractorImp): LoginConfirmInteractor

    @Binds
    abstract fun bindRepository(impl: LoginConfirmRepositoryImp): LoginConfirmRepository

    companion object {
        @Provides
        fun provideService(
            retrofit: Retrofit
        ): LoginConfirmService = retrofit.create(LoginConfirmService::class.java)
    }
}
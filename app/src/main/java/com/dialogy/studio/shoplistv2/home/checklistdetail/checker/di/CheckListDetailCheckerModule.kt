package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.di

import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.CheckListDetailCheckerRepository
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.ICheckListDetailCheckerService
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.data.ICheckListDetailCheckerRepository
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.CheckListDetailCheckerInteractor
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.ICheckListDetailCheckerInteractor
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.mapper.CheckListDetailCheckerMapper
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.domain.mapper.ICheckListDetailCheckerMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
abstract class CheckListDetailCheckerModule {
    @Binds
    abstract fun bindRepository(repository: CheckListDetailCheckerRepository): ICheckListDetailCheckerRepository

    @Binds
    abstract fun bindInteractor(interactor: CheckListDetailCheckerInteractor): ICheckListDetailCheckerInteractor

    @Binds
    abstract fun bindMapper(mapper: CheckListDetailCheckerMapper): ICheckListDetailCheckerMapper

    companion object {
        @Provides
        fun provideService(
            retrofit: Retrofit
        ): ICheckListDetailCheckerService = retrofit.create(ICheckListDetailCheckerService::class.java)
    }
}
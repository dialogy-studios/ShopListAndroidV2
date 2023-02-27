package com.dialogy.studio.shoplistv2.home.checklist.home.di

import com.dialogy.studio.shoplistv2.home.checklist.home.data.CheckListRepository
import com.dialogy.studio.shoplistv2.home.checklist.home.data.ICheckListRepository
import com.dialogy.studio.shoplistv2.home.checklist.home.data.ICheckListService
import com.dialogy.studio.shoplistv2.home.checklist.home.domain.CheckListInteractor
import com.dialogy.studio.shoplistv2.home.checklist.home.domain.ICheckListInteractor
import com.dialogy.studio.shoplistv2.home.checklist.home.domain.mapper.CheckListComponentRVMapper
import com.dialogy.studio.shoplistv2.home.checklist.home.domain.mapper.ICheckListComponentRVMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
abstract class CheckListModule {
    @Binds
    abstract fun bindsRepository(repository: CheckListRepository): ICheckListRepository

    @Binds
    abstract fun bindsInteractor(interactor: CheckListInteractor): ICheckListInteractor

    @Binds
    abstract fun bindsMapper(mapper: CheckListComponentRVMapper): ICheckListComponentRVMapper

    companion object {
        @Provides
        fun provideService(
            retrofit: Retrofit
        ): ICheckListService =
            retrofit.create(ICheckListService::class.java)
    }
}
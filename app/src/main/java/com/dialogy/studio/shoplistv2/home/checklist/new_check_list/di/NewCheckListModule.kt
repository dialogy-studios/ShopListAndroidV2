package com.dialogy.studio.shoplistv2.home.checklist.new_check_list.di

import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.data.NewCheckListRepository
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.data.NewCheckListRepositoryImp
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.data.NewCheckListService
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.domain.NewCheckListInteractor
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.domain.NewCheckListInteractorImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewCheckListModule {
    @Binds
    abstract fun bindInteractor(impl: NewCheckListInteractorImp): NewCheckListInteractor

    @Binds
    abstract fun bindRepository(impl: NewCheckListRepositoryImp): NewCheckListRepository

    companion object {
        @Provides
        fun providesService(
            retrofit: Retrofit
        ): NewCheckListService = retrofit.create(NewCheckListService::class.java)
    }
}
package com.dialogy.studio.shoplistv2.home.checklist.new_check_list.domain

import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.data.NewCheckListRepository
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation.model.NewCheckListInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

interface NewCheckListInteractor {
    fun save(input: NewCheckListInput): Flow<String>

}

class NewCheckListInteractorImp @Inject constructor(
    private val repository: NewCheckListRepository
): NewCheckListInteractor  {
    override fun save(input: NewCheckListInput): Flow<String> = flow {
        repository.save(input).await()
        this.emit("")
    }
}
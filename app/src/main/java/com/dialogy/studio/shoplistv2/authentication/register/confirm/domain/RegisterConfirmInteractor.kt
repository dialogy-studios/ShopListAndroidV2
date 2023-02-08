package com.dialogy.studio.shoplistv2.authentication.register.confirm.domain

import com.dialogy.studio.shoplistv2.authentication.register.confirm.data.RegisterConfirmRepository
import com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation.model.RegisterConfirmInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RegisterConfirmInteractor  {
    fun verify(payload: RegisterConfirmInput): Flow<Boolean>
}

class RegisterConfirmInteractorImp @Inject constructor(
    private val repository: RegisterConfirmRepository
) : RegisterConfirmInteractor {
    override fun verify(payload: RegisterConfirmInput): Flow<Boolean> =
        flow {
            val response = repository.verify(payload)
            emit(true)
        }

}
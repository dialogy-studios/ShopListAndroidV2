package com.dialogy.studio.shoplistv2.authentication.login.confirm.domain

import com.dialogy.studio.shoplistv2.authentication.login.confirm.data.LoginConfirmRepository
import com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation.model.LoginConfirmInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

interface LoginConfirmInteractor {
    fun confirm(input: LoginConfirmInput): Flow<String>
}

class LoginConfirmInteractorImp @Inject constructor(
    private val repository: LoginConfirmRepository
) : LoginConfirmInteractor {
    override fun confirm(input: LoginConfirmInput): Flow<String> =
        flow {
            val authToken = repository.confirm(input).await()
            emit(authToken)
        }

}
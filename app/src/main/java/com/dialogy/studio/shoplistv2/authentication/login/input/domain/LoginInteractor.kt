package com.dialogy.studio.shoplistv2.authentication.login.input.domain

import com.dialogy.studio.shoplistv2.authentication.login.input.data.LoginRepository
import com.dialogy.studio.shoplistv2.authentication.login.input.presentation.model.LoginInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

interface LoginInteractor {
    fun login(input: LoginInput): Flow<String>
}

class LoginInteractorImp @Inject constructor(
    private val repository: LoginRepository
): LoginInteractor {
    override fun login(input: LoginInput): Flow<String> =
        flow {
            val response = repository.login(input).await()
            emit(response)
        }
}
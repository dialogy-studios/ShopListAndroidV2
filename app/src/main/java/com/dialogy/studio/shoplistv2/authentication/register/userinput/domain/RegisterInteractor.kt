package com.dialogy.studio.shoplistv2.authentication.register.userinput.domain

import com.dialogy.studio.shoplistv2.authentication.register.userinput.data.RegisterRepository
import com.dialogy.studio.shoplistv2.authentication.register.userinput.presentation.model.UserRegistrationInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

interface RegisterInteractor {
    fun register(payload: UserRegistrationInput): Flow<String>
}

class RegisterInteractorImp @Inject constructor(
    private val repository: RegisterRepository,
) : RegisterInteractor {
    override fun register(payload: UserRegistrationInput): Flow<String> =
        flow {
            val response = repository.register(payload).await()
            emit(response.orEmpty())
        }
}
package com.dialogy.studio.shoplistv2.authentication.register.presentation.domain

import com.dialogy.studio.shoplistv2.authentication.register.presentation.data.RegisterRepository
import com.dialogy.studio.shoplistv2.authentication.register.presentation.data.model.UserRegistrationOutput
import com.dialogy.studio.shoplistv2.authentication.register.presentation.model.UserRegistrationInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

interface RegisterInteractor {
    fun register(payload: UserRegistrationInput): Flow<UserRegistrationOutput>
}

class RegisterInteractorImp @Inject constructor(
    private val repository: RegisterRepository,
    private val errorMapper: RegisterErrorMapper
) : RegisterInteractor {
    override fun register(payload: UserRegistrationInput): Flow<UserRegistrationOutput> =
        flow {
            val response = repository.register(payload).await()
            if (response.isSuccessful) {
                val output = UserRegistrationOutput(
                    success = true
                )
                emit(output)
            }
            val error = errorMapper.map(response.body().orEmpty())
            throw error.throwable
        }
}
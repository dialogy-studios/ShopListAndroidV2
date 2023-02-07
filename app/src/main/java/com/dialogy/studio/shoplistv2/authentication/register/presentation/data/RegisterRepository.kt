package com.dialogy.studio.shoplistv2.authentication.register.presentation.data

import com.dialogy.studio.shoplistv2.authentication.register.presentation.model.UserRegistrationInput
import retrofit2.Call
import javax.inject.Inject

interface RegisterRepository {
    fun register(payload: UserRegistrationInput): Call<String?>
}

class RegisterRepositoryImp @Inject constructor(
    private val service: RegisterService
): RegisterRepository {
    override fun register(payload: UserRegistrationInput): Call<String?> =
        service.register(payload)
}
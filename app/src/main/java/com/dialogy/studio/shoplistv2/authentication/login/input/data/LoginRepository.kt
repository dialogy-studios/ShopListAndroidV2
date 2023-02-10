package com.dialogy.studio.shoplistv2.authentication.login.input.data

import com.dialogy.studio.shoplistv2.authentication.login.input.presentation.model.LoginInput
import retrofit2.Call
import javax.inject.Inject

interface LoginRepository {
    fun login(input: LoginInput): Call<String>
}

class LoginRepositoryImp @Inject constructor(
    private val service: LoginService
) : LoginRepository {
    override fun login(input: LoginInput): Call<String> =
        service.login(input)
}
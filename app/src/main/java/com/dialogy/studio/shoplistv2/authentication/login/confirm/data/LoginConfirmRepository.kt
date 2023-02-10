package com.dialogy.studio.shoplistv2.authentication.login.confirm.data

import com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation.model.LoginConfirmInput
import retrofit2.Call
import javax.inject.Inject

interface LoginConfirmRepository {
    fun confirm(input: LoginConfirmInput): Call<String>
}

class LoginConfirmRepositoryImp @Inject constructor(
    private val service: LoginConfirmService
) : LoginConfirmRepository {
    override fun confirm(input: LoginConfirmInput): Call<String> =
        service.confirm(input)
}
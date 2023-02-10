package com.dialogy.studio.shoplistv2.authentication.login.confirm.data

import com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation.model.LoginConfirmInput
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginConfirmService {
    @POST("/login/confirm")
    fun confirm(
        @Body
        input: LoginConfirmInput
    ) : Call<String>
}
package com.dialogy.studio.shoplistv2.authentication.login.input.data

import com.dialogy.studio.shoplistv2.authentication.login.input.presentation.model.LoginInput
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    fun login(
        @Body
        input: LoginInput
    ): Call<String>
}
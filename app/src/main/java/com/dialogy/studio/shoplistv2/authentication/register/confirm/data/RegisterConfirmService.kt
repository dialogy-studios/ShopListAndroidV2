package com.dialogy.studio.shoplistv2.authentication.register.confirm.data

import com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation.model.RegisterConfirmInput
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterConfirmService {
    @POST("/register/confirm")
    fun verify(
        @Body
        payload: RegisterConfirmInput
    ): Call<String?>
}
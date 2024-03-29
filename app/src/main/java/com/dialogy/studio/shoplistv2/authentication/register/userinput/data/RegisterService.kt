package com.dialogy.studio.shoplistv2.authentication.register.userinput.data

import com.dialogy.studio.shoplistv2.authentication.register.userinput.presentation.model.UserRegistrationInput
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("/register")
    fun register(
        @Body payload: UserRegistrationInput
    ): Call<String?>
}
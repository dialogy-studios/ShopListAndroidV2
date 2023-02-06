package com.dialogy.studio.shoplistv2.authentication.register.presentation.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface RegisterService {
    @POST("/register")
    fun register(
        @Field(value = "username") username: String,
        @Field(value = "password") password: String,
        @Field(value = "name") name: String,
        @Field(value = "lastname") lastname: String,
        @Field(value = "phoneNumber") phoneNumber: String,
    ): Call<Response<String>>
}
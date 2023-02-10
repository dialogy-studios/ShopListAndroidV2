package com.dialogy.studio.shoplistv2.authentication.login.input.presentation.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginInput(
    val username: String = "",
    val password: String = ""
)
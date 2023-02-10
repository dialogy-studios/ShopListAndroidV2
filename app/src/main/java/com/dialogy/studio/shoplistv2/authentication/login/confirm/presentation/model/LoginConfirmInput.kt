package com.dialogy.studio.shoplistv2.authentication.login.confirm.presentation.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginConfirmInput(
    val username: String = "",
    val confirmationCode: String = ""
)
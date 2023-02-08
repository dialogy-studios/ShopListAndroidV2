package com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterConfirmInput(
    val confirmationCode: String = "",
    val username: String = ""
)
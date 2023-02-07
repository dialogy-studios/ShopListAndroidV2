package com.dialogy.studio.shoplistv2.authentication.register.userinput.presentation.model

import com.dialogy.studio.shoplistv2.strings.extensions.isValidEmail
import com.dialogy.studio.shoplistv2.strings.extensions.isValidPassword
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRegistrationInput(
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val lastname: String = "",
    val phoneNumber: String = ""
) {
    fun areAllFieldsValid(): Boolean {
        return username.isValidEmail() &&
                password.isValidPassword() &&
                name.isNotEmpty() &&
                lastname.isNotEmpty() &&
                phoneNumber.isNotEmpty()
    }
}
package com.dialogy.studio.shoplistv2.authentication.register.presentation.model

import com.dialogy.studio.shoplistv2.strings.extensions.isValidEmail
import com.dialogy.studio.shoplistv2.strings.extensions.isValidPassword

data class UserRegistrationInput(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val lastname: String = "",
    val phoneNumber: String = ""
) {
    fun areAllFieldsValid(): Boolean {
        return email.isValidEmail() &&
                password.isValidPassword() &&
                name.isNotEmpty() &&
                lastname.isNotEmpty() &&
                phoneNumber.isNotEmpty()
    }
}
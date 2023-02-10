package com.dialogy.studio.shoplistv2.authentication.login.input.domain.model

enum class LoginThrowable(val id: String, val exception: Exception) {
    UsernameInvalid("UsernameInvalid", UsernameInvalidException()),
    PasswordInvalid("PasswordInvalid", PasswordInvalidException()),
    UserNotFound("UserNotFoundException", UserNotFoundException()),
    NotAuthorized("NotAuthorizedException", NotAuthorizedException()),
    Unknown("Unknown", Exception());

    companion object {
        fun fromId(targetId: String) = values().firstOrNull { it.id == targetId } ?: Unknown
    }
}
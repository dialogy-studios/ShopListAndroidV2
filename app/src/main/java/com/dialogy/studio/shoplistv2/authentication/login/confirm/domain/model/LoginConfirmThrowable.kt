package com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.model

enum class LoginConfirmThrowable(val id: String, val exception: Exception) {
    UsernameInvalid("UsernameInvalid", UsernameInvalidException()),
    ConfirmationCodeInvalid("ConfirmationCodeInvalid", ConfirmationCodeInvalidException()),
    CodeMismatch("CodeMismatchException", CodeMismatchException()),
    ExpiredCode("ExpiredCodeException", ExpiredCodeException()),
    Unknown("", Exception());

    companion object {
        fun fromId(targetId: String) = values().firstOrNull { it.id == targetId } ?: Unknown
    }

}
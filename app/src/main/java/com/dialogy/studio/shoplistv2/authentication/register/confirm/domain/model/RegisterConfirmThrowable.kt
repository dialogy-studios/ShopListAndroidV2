package com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.model

enum class RegisterConfirmThrowable(val id: String, val exception: Exception) {
    ConfirmationCodeInvalid("ConfirmationCodeInvalid", ConfirmationCodeInvalidException()),
    CodeMismatch("CodeMismatchException", CodeMismatchException()),
    ExpiredCode("ExpiredCodeException", ExpiredCodeException()),
    Unknown("", Exception());
    companion object {
        fun fromId(targetId: String) = values().firstOrNull { it.id == targetId } ?: Unknown
    }
}
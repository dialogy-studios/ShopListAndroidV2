package com.dialogy.studio.shoplistv2.authentication.register.userinput.domain.model

enum class RegistrationThrowable(val id: String, val throwable: Throwable) {
    UsernameExistsException("UsernameExistsException", UsernameExistsException()),
    InvalidParameterException("InvalidParameterException", InvalidParameterException()),
    NameInvalid("NameInvalid", NameInvalidException()),
    LastNameInvalid("LastNameInvalid", LastNameInvalidException()),
    UsernameInvalid("UsernameInvalid", EmailInvalidException()),
    PhoneNumberInvalid("PhoneNumberInvalid", PhoneNumberInvalidException()),
    PasswordInvalid("PasswordInvalid", PasswordInvalidException()),
    EmailInvalid("EmailInvalid", EmailInvalidException()),
    PhoneNumberConflict("PHONE_NUMBER_CONFLICT", PhoneNumberConflictException()),
    Unknown("unknown", UnknownException());

    companion object {
        fun fromId(targetId: String) = values().firstOrNull { targetId == it.id } ?: Unknown
    }
}


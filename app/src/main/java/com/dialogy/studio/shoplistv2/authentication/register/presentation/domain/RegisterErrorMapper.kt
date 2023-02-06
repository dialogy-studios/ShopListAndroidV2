package com.dialogy.studio.shoplistv2.authentication.register.presentation.domain

import com.dialogy.studio.shoplistv2.authentication.register.presentation.domain.model.RegistrationThrowable
import javax.inject.Inject

interface RegisterErrorMapper {
    fun map(value: String): RegistrationThrowable
}

class RegisterErrorMapperImp @Inject constructor() : RegisterErrorMapper {
    override fun map(value: String): RegistrationThrowable =
        RegistrationThrowable.fromId(value)
}
package com.dialogy.studio.shoplistv2.authentication.register.userinput.domain

import com.dialogy.studio.shoplistv2.authentication.register.userinput.domain.model.RegistrationThrowable
import javax.inject.Inject

interface RegisterErrorMapper {
    fun map(value: String): RegistrationThrowable
}

class RegisterErrorMapperImp @Inject constructor() : RegisterErrorMapper {
    override fun map(value: String): RegistrationThrowable =
        RegistrationThrowable.fromId(value)
}
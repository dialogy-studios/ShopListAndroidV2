package com.dialogy.studio.shoplistv2.authentication.login.input.domain

import com.dialogy.studio.shoplistv2.authentication.login.input.domain.model.LoginThrowable
import javax.inject.Inject

interface LoginErrorMapper {
    fun map(id: String): LoginThrowable
}

class LoginErrorMapperImp @Inject constructor() : LoginErrorMapper {
    override fun map(id: String): LoginThrowable =
        LoginThrowable.fromId(id)
}
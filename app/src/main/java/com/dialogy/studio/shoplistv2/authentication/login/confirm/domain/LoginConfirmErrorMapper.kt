package com.dialogy.studio.shoplistv2.authentication.login.confirm.domain

import com.dialogy.studio.shoplistv2.authentication.login.confirm.domain.model.LoginConfirmThrowable
import javax.inject.Inject

interface LoginConfirmErrorMapper {
    fun map(id: String): LoginConfirmThrowable
}

class LoginConfirmErrorMapperImp @Inject constructor() : LoginConfirmErrorMapper {
    override fun map(id: String): LoginConfirmThrowable =
        LoginConfirmThrowable.fromId(id)
}
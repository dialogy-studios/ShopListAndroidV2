package com.dialogy.studio.shoplistv2.authentication.register.confirm.domain

import com.dialogy.studio.shoplistv2.authentication.register.confirm.domain.model.RegisterConfirmThrowable
import javax.inject.Inject

interface RegisterConfirmErrorMapper {
    fun map(id: String): RegisterConfirmThrowable
}

class RegisterConfirmErrorMapperImp @Inject constructor() : RegisterConfirmErrorMapper {
    override fun map(id: String): RegisterConfirmThrowable =
        RegisterConfirmThrowable.fromId(id)
}
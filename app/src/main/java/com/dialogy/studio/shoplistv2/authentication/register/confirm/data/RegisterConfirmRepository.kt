package com.dialogy.studio.shoplistv2.authentication.register.confirm.data

import com.dialogy.studio.shoplistv2.authentication.register.confirm.presentation.model.RegisterConfirmInput
import retrofit2.Call
import javax.inject.Inject

interface RegisterConfirmRepository {
    fun verify(payload: RegisterConfirmInput): Call<String?>
}

class RegisterConfirmRepositoryImp @Inject constructor(
    private val service: RegisterConfirmService
): RegisterConfirmRepository {
    override fun verify(payload: RegisterConfirmInput): Call<String?> =
        service.verify(payload)
}
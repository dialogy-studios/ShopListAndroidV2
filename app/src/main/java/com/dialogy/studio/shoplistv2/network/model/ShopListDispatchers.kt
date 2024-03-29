package com.dialogy.studio.shoplistv2.network.model

import kotlinx.coroutines.CoroutineDispatcher

data class ShopListDispatchers(
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val default: CoroutineDispatcher
)
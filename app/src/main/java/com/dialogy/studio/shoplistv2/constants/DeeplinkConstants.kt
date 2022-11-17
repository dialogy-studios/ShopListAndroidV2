package com.dialogy.studio.shoplistv2.constants

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.dialogy.studio.shoplistv2.R

object DeeplinkConstants {
    fun SCHEME(context: Context) = context.getString(R.string.app_scheme)
    fun EMIT_CATEGORY_DETAIL_PRODUCTS_DEEPLINK(context: Context, categoryId: String) = emitDeeplink(context, "${SCHEME(context)}://category_detail/${categoryId}")
    private fun emitDeeplink(context: Context, deeplink: String) {
        startActivity(
            context,
            Intent().apply {
                data = deeplink.toUri()
            },
            null
        )
    }
}
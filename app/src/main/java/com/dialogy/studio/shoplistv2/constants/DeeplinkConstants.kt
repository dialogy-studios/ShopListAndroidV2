package com.dialogy.studio.shoplistv2.constants

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.dialogy.studio.shoplistv2.R

object DeeplinkConstants {
    fun SCHEME(context: Context) = context.getString(R.string.app_scheme)

    object CATEGORY_DETAIL {
        fun emit(context: Context, categoryId: String) = emitDeeplink(context, "${SCHEME(context)}://category_detail?${QueryParam.CATEGORY_ID.id}=${categoryId}")
        enum class QueryParam(val id: String) {
            CATEGORY_ID("category-id")
        }
    }

    object CHECK_LIST_DETAIL {
        fun emit(context: Context, checkListId: String) = emitDeeplink(context, "${SCHEME(context)}://check_list_detail?${QueryParam.CHECK_LIST_ID.id}=${checkListId}")
        enum class QueryParam(val id: String) {
            CHECK_LIST_ID("check-list-id")
        }
    }
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
package com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewCheckListInput(
    val name: String = ""
)
package com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.model

interface ICheckListVHListener {
    val cardListener: ICardListener
}

interface ICardListener {
    fun onClick(vo: CheckListVO)
}
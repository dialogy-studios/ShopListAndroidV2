package com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list

import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.CheckListItemVhBinding
import com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list.model.CheckListVO
import com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list.model.ICheckListVHListener
import com.dialogy.studio.shoplistv2.home.checklist.presentation.component.selectedproductsshowcase.SelectedProductsShowCaseRVAdapter

class CheckListVH(
    private val binding: CheckListItemVhBinding,
    private val listener: ICheckListVHListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vo: CheckListVO) {
        with (binding) {
            checkListTitle.text = vo.title
            productsCount.text = vo.selectedProductsShowCase.size.toString()
            totalPrice.text = vo.checkListTotal
            selectedProductsShowcaseRv.adapter = SelectedProductsShowCaseRVAdapter(vo.selectedProductsShowCase)
            cardContainer.setOnClickListener {
                listener.cardListener.onClick(vo)
            }
        }
    }
}
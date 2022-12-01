package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dialogy.studio.shoplistv2.components.textview.strike
import com.dialogy.studio.shoplistv2.databinding.CheckListDetailCheckerSelectedProductItemVhBinding
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model.CheckListDetailCheckerVO
import com.google.android.material.checkbox.MaterialCheckBox

class CheckListDetailCheckerVH(
    private val binding: CheckListDetailCheckerSelectedProductItemVhBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vo: CheckListDetailCheckerVO) {
        val context = binding.root.context
        with (binding) {
            productName.text = vo.productName
            productPrice.text = vo.productPrice
            checker.isChecked = vo.isChecked
            Glide.with(context).load(vo.thumb).into(thumb)
            updateCheckedStyle(vo.isChecked)
            checker.addOnCheckedStateChangedListener { checkBox, state ->
                updateCheckedStyle((state == MaterialCheckBox.STATE_CHECKED))
            }
        }
    }

    private fun updateCheckedStyle(isChecked: Boolean) {
        with (binding) {
            productName.strike = isChecked == true
            productPrice.strike = isChecked == true
        }
    }

}
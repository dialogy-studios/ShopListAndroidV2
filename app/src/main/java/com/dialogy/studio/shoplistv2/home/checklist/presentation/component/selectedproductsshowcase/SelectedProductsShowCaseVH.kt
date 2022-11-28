package com.dialogy.studio.shoplistv2.home.checklist.presentation.component.selectedproductsshowcase

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dialogy.studio.shoplistv2.databinding.SelectedProductShowCaseItemVhBinding

class SelectedProductsShowCaseVH(
    private val binding: SelectedProductShowCaseItemVhBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(thumb: String) {
        val context = binding.root.context
        Glide.with(context).load(thumb).into(binding.thumb)
    }
}
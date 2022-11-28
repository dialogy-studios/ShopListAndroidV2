package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dialogy.studio.shoplistv2.databinding.CategoryProductVhBinding
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model.CategoryProductVO

class CategoryProductVH(
    private val binding: CategoryProductVhBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vo: CategoryProductVO) {
        val context = binding.root.context
        Glide.with(context).load(vo.thumb).into(binding.thumb)
        binding.thumb.setOnClickListener {
            vo.listener?.thumb?.onClick()
        }
    }
}
package com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist

import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.VerticalCategoryListBinding
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.CategoryVerticalListVO

class VerticalCategoryListViewHolder(
    private val binding: VerticalCategoryListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vo: CategoryVerticalListVO) {
        binding.categoryName.text = vo.name
        binding.productList.vo = vo.productList
        binding.seeMore.setOnClickListener {
            vo.listener.seeMore?.onClick()
        }
    }
}
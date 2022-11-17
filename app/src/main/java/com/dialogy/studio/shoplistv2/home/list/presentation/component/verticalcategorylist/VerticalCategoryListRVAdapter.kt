package com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.VerticalCategoryListBinding
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.CategoryVerticalListVO

class VerticalCategoryListRVAdapter(
    private val data: List<CategoryVerticalListVO>
): RecyclerView.Adapter<VerticalCategoryListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VerticalCategoryListViewHolder {
        val binding = VerticalCategoryListBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return VerticalCategoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VerticalCategoryListViewHolder, position: Int) {
        val vo = data[position]
        holder.bind(vo)
    }

    override fun getItemCount(): Int = data.size
}
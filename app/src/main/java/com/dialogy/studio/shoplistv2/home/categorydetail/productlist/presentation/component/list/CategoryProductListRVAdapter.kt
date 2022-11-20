package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.CategoryProductVhBinding
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model.CategoryProductVO

class CategoryProductListRVAdapter(
    private val data: List<CategoryProductVO>
) : RecyclerView.Adapter<CategoryProductVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryProductVhBinding.inflate(inflater)
        return CategoryProductVH(binding)
    }

    override fun onBindViewHolder(holder: CategoryProductVH, position: Int) {
        val vo = data[position]
        holder.bind(vo)
    }

    override fun getItemCount(): Int = data.size
}
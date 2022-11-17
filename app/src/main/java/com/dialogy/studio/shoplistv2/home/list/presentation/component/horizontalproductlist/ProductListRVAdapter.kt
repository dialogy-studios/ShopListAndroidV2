package com.dialogy.studio.shoplistv2.home.list.presentation.component.horizontalproductlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.ProductViewHolderBinding
import com.dialogy.studio.shoplistv2.home.model.ProductResponse

class ProductListRVAdapter(
    private val data: List<ProductListVO>
) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductViewHolderBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productVo = data[position]
        holder.bind(productVo)
    }

    override fun getItemCount(): Int = data.size
}
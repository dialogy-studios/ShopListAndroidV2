package com.dialogy.studio.shoplistv2.home.list.presentation.component.horizontalproductlist

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dialogy.studio.shoplistv2.databinding.ProductViewHolderBinding
import com.dialogy.studio.shoplistv2.home.model.ProductResponse
import com.google.android.material.snackbar.Snackbar

class ProductViewHolder(
    val binding: ProductViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(productVO: ProductListVO) {
        val context = binding.thumb.context
        Glide.with(context).load(productVO.thumb).into(binding.thumb)
        binding.thumb.setOnClickListener {
            Snackbar.make(binding.root, "Clicked!!", Toast.LENGTH_SHORT).show()
        }

    }
}
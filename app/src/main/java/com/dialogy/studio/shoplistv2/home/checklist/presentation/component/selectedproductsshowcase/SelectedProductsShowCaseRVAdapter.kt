package com.dialogy.studio.shoplistv2.home.checklist.presentation.component.selectedproductsshowcase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.SelectedProductShowCaseItemVhBinding

class SelectedProductsShowCaseRVAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<SelectedProductsShowCaseVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedProductsShowCaseVH {
        val inflater = LayoutInflater.from(parent.context)
        return SelectedProductsShowCaseVH(
            SelectedProductShowCaseItemVhBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SelectedProductsShowCaseVH, position: Int) {
        val thumb = data[position]
        holder.bind(thumb)
    }

    override fun getItemCount(): Int =
        data.size
}
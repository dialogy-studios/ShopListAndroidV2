package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.CheckListDetailCheckerSelectedProductItemVhBinding
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.model.CheckListDetailCheckerVO

class CheckListDetailCheckerRVAdapter(
    private val data: List<CheckListDetailCheckerVO>
) : RecyclerView.Adapter<CheckListDetailCheckerVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListDetailCheckerVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CheckListDetailCheckerSelectedProductItemVhBinding.inflate(inflater, parent, false)
        return CheckListDetailCheckerVH(binding)
    }

    override fun onBindViewHolder(holder: CheckListDetailCheckerVH, position: Int) {
        val vo = data[position]
        holder.bind(vo)
    }

    override fun getItemCount(): Int =
        data.size
}
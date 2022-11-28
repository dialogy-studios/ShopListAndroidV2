package com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dialogy.studio.shoplistv2.databinding.CheckListItemVhBinding
import com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list.model.CheckListVO
import com.dialogy.studio.shoplistv2.home.checklist.presentation.component.list.model.ICheckListVHListener

class CheckListRVAdapter(
    private val data: List<CheckListVO>,
    private val listener: ICheckListVHListener
) : RecyclerView.Adapter<CheckListVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListVH {
        val inflater = LayoutInflater.from(parent.context)
        return CheckListVH(
            CheckListItemVhBinding.inflate(inflater, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: CheckListVH, position: Int) {
        val vo = data[position]
        holder.bind(vo)
    }

    override fun getItemCount(): Int = data.size
}
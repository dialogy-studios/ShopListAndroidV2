package com.dialogy.studio.shoplistv2.home.list.presentation.component.horizontalproductlist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dialogy.studio.shoplistv2.R
import com.dialogy.studio.shoplistv2.databinding.HorizontalProductListBinding
import com.dialogy.studio.shoplistv2.home.model.ProductResponse

class HorizontalProductList @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding by lazy {
        HorizontalProductListBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    var vo: List<ProductListVO>? = null
        set(value) {
            field = value
            if (value != null) {
                renderVO(value)
            }
        }

    init {
        setup()
    }

    private fun setup() {
        setupTheme()
    }

    private fun setupTheme() {
        context.theme.obtainStyledAttributes(attrs, R.styleable.HorizontalProductList, defStyleAttr, defStyleRes).apply {

        }.recycle()
    }

    private fun renderVO(value: List<ProductListVO>) {
        binding.productListRv.adapter = ProductListRVAdapter(value)
    }

}
package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dialogy.studio.shoplistv2.constants.AUTHENTICATION_SHARED_PREFERENCES_KEY
import com.dialogy.studio.shoplistv2.constants.AUTHORIZATION_KEY
import com.dialogy.studio.shoplistv2.constants.DeeplinkConstants
import com.dialogy.studio.shoplistv2.databinding.CategoryProductListFragmentBinding
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.CategoryProductListRVAdapter
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.model.ICategoryProductListener
import com.dialogy.studio.shoplistv2.listener.IButtonListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryProductListFragment : Fragment() {
    private var binding: CategoryProductListFragmentBinding? = null
    private val vm: CategoryProductListViewModel by viewModels()
    val authorization: String by lazy {
        context?.getSharedPreferences(AUTHENTICATION_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)?.getString(
            AUTHORIZATION_KEY, ""
        ).orEmpty()
    }
    private val categoryId: String by lazy {
        activity?.intent?.data?.getQueryParameter(DeeplinkConstants.CATEGORY_DETAIL.QueryParam.CATEGORY_ID.id) ?: UNKNOWN_CATEGORY_ID
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryProductListFragmentBinding.inflate(inflater, container, false)
        setup()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setup() {
        setupObserver()
    }

    override fun onStart() {
        super.onStart()
        vm.fetchProductList(categoryId, authorization)
    }

    private fun setupObserver() {
        vm.state.observe(viewLifecycleOwner) { newState -> handleNewState(newState) }
    }

    private fun handleNewState(state: CategoryProductListViewModel.CategoryProductListState) {
        when (state) {
            is CategoryProductListViewModel.CategoryProductListState.UI -> handleUIState(state)
        }
    }

    private fun handleUIState(state: CategoryProductListViewModel.CategoryProductListState.UI) {
        when (state) {
            is CategoryProductListViewModel.CategoryProductListState.UI.Success -> handleUISuccessState(state)
            is CategoryProductListViewModel.CategoryProductListState.UI.Loading -> handleUILoadingState(state)
            is CategoryProductListViewModel.CategoryProductListState.UI.Error -> handleUIErrorState(state)
        }
    }

    private fun handleUISuccessState(state: CategoryProductListViewModel.CategoryProductListState.UI.Success) {
        state.vo.copy(id= state.vo.id, productList= state.vo.productList.map {
            it.copy(listener = object : ICategoryProductListener {
                override val thumb: IButtonListener
                    get() = object : IButtonListener {
                        override fun onClick() {
                            binding?.root?.let { view ->
                                Snackbar.make(view, "Clicked on thumb!", Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }

            })
        })
            .apply {
                binding?.productListRecyclerView?.adapter = CategoryProductListRVAdapter(productList)
            }
    }

    private fun handleUILoadingState(state: CategoryProductListViewModel.CategoryProductListState.UI.Loading) {

    }

    private fun handleUIErrorState(state: CategoryProductListViewModel.CategoryProductListState.UI.Error) {

    }

}

const val UNKNOWN_CATEGORY_ID = ""
package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dialogy.studio.shoplistv2.constants.DeeplinkConstants
import com.dialogy.studio.shoplistv2.databinding.CategoryProductListFragmentBinding
import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.presentation.component.list.CategoryProductListRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryProductListFragment : Fragment() {
    private var binding: CategoryProductListFragmentBinding? = null
    private val vm: CategoryProductListViewModel by viewModels()

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
        vm.fetchProductList(categoryId)
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
        binding?.productListRecyclerView?.adapter = CategoryProductListRVAdapter(state.vo.productList)
    }

    private fun handleUILoadingState(state: CategoryProductListViewModel.CategoryProductListState.UI.Loading) {

    }

    private fun handleUIErrorState(state: CategoryProductListViewModel.CategoryProductListState.UI.Error) {

    }

}

const val UNKNOWN_CATEGORY_ID = ""
package com.dialogy.studio.shoplistv2.home.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dialogy.studio.shoplistv2.constants.DeeplinkConstants
import com.dialogy.studio.shoplistv2.databinding.ListFragmentBinding
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.VerticalCategoryListRVAdapter
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.ICategoryVerticalListListener
import com.dialogy.studio.shoplistv2.home.list.presentation.component.verticalcategorylist.model.CategoryVerticalListVO
import com.dialogy.studio.shoplistv2.listener.IButtonListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    var binding : ListFragmentBinding? = null
    val vm: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater)
        setup()
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        fetchList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setup() {
        setupObserver()
    }

    private fun setupObserver() {
        vm.state.observe(viewLifecycleOwner) { newState ->
            handleState(newState)
        }
    }

    private fun handleState(state: ListViewModel.State) {
        when (state) {
            is ListViewModel.State.UI.Success -> {
                renderCategoryList(state.data)
            }
            is ListViewModel.State.UI.Error -> {}
            is ListViewModel.State.UI.Loading -> {}
            else -> {}
        }
    }

    private fun fetchList() {
        vm.fetchList()
    }

    private fun renderCategoryList(verticalCategoryList: List<CategoryVerticalListVO>) {
        verticalCategoryList.map {
            it.copy(
                listener = object : ICategoryVerticalListListener {
                    override val seeMore: IButtonListener
                        get() = object : IButtonListener {
                            override fun onClick() {
                                DeeplinkConstants.EMIT_CATEGORY_DETAIL_PRODUCTS_DEEPLINK(
                                    context = requireContext(),
                                    categoryId = it.id
                                )
                            }
                        }

                }
            )
        }.let {
            binding?.container?.adapter = VerticalCategoryListRVAdapter(it)
        }
    }
}
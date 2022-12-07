package com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dialogy.studio.shoplistv2.constants.DeeplinkConstants
import com.dialogy.studio.shoplistv2.currency.domain.formatter.ICurrencyFormatter
import com.dialogy.studio.shoplistv2.databinding.CheckListDetailCheckerFragmentBinding
import com.dialogy.studio.shoplistv2.home.checklistdetail.checker.presentation.components.selectedproductsrv.CheckListDetailCheckerRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CheckListDetailCheckerFragment : Fragment() {
    private var binding : CheckListDetailCheckerFragmentBinding? = null
    private val vm: CheckListDetailCheckerViewModel by viewModels()
    @Inject
    lateinit var currencyFormatter: ICurrencyFormatter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CheckListDetailCheckerFragmentBinding.inflate(inflater, container, false)
        setup()
        fetchCheckListDetail()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setup() {
        setupObservers()
    }

    private fun setupObservers() {
        vm.state.observe(viewLifecycleOwner) { newState ->
            handleNewState(newState)
        }
    }

    private fun handleNewState(state: CheckListDetailCheckerViewModel.State) {
        when (state) {
            is CheckListDetailCheckerViewModel.State.UI -> handleUiState(state)
        }
    }

    private fun handleUiState(state: CheckListDetailCheckerViewModel.State.UI) {
        when (state) {
            is CheckListDetailCheckerViewModel.State.UI.Success -> handleSuccessState(state)
            is CheckListDetailCheckerViewModel.State.UI.Loading -> handleLoading(state)
            is CheckListDetailCheckerViewModel.State.UI.Error -> handleError(state)
        }
    }

    private fun handleSuccessState(state: CheckListDetailCheckerViewModel.State.UI.Success) {
        binding?.selectedProductsChecker?.adapter = CheckListDetailCheckerRVAdapter(state.vo.selectedProductsVO)
        binding?.totalPrice?.text = currencyFormatter.format(state.vo.totalPriceVO.price)
    }
    private fun handleError(state: CheckListDetailCheckerViewModel.State.UI.Error) {}
    private fun handleLoading(state: CheckListDetailCheckerViewModel.State.UI.Loading) {}

    private fun fetchCheckListDetail() {
        vm.fetchCheckListDetail(activity?.intent?.data?.getQueryParameter(DeeplinkConstants.CHECK_LIST_DETAIL.QueryParam.CHECK_LIST_ID.id).orEmpty())
    }
}
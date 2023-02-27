package com.dialogy.studio.shoplistv2.home.checklist.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dialogy.studio.shoplistv2.constants.DeeplinkConstants
import com.dialogy.studio.shoplistv2.databinding.ChecklistFragmentBinding
import com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.CheckListRVAdapter
import com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.model.CheckListVO
import com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.model.ICardListener
import com.dialogy.studio.shoplistv2.home.checklist.home.presentation.component.list.model.ICheckListVHListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckListFragment : Fragment() {
    private var binding: ChecklistFragmentBinding? = null
    private val vm: CheckListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChecklistFragmentBinding.inflate(inflater, container, false)
        setup()
        requestData()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setup() {
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {

    }

    private fun setupObservers() {
        vm.state.observe(viewLifecycleOwner) {
            handleNewState(it)
        }
    }

    private fun requestData() {
        vm.fetchCheckList()
    }

    private fun handleNewState(state: CheckListViewModel.State) {
        when (state) {
            is CheckListViewModel.State.UI -> handleUIState(state)
        }
    }

    private fun handleUIState(state: CheckListViewModel.State.UI) {
        when (state) {
            is CheckListViewModel.State.UI.Success -> handleSuccessState(state)
            is CheckListViewModel.State.UI.Loading -> handleLoadingState(state)
            is CheckListViewModel.State.UI.Error -> handleErrorState(state)
        }
    }

    private fun handleSuccessState(state: CheckListViewModel.State.UI.Success) {
        binding?.checkListRv?.adapter = CheckListRVAdapter(state.screenVO, object:
            ICheckListVHListener {
            override val cardListener: ICardListener
                get() = object: ICardListener {
                    override fun onClick(vo: CheckListVO) {
                        binding?.root?.let {
                            DeeplinkConstants.CHECK_LIST_DETAIL.emit(requireContext(), "")
                        }
                    }
                }

        })
    }

    private fun handleLoadingState(state: CheckListViewModel.State.UI.Loading) {}

    private fun handleErrorState(state: CheckListViewModel.State.UI.Error) {}

}
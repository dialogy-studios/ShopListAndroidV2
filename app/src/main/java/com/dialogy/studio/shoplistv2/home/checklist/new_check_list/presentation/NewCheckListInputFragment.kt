package com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dialogy.studio.shoplistv2.databinding.NewCheckListFragmentBinding
import com.dialogy.studio.shoplistv2.home.checklist.new_check_list.presentation.model.NewCheckListInput
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewCheckListInputFragment : Fragment() {

    companion object {
        const val NORMAL = 0
        const val LOADING = 1
        const val SUCCESS = 2
    }

    private val vm by viewModels<NewCheckListInputViewModel>()
    private var binding: NewCheckListFragmentBinding? = null
    private val normalLayout by lazy {
        binding?.normal
    }
    private val sucessLayout by lazy {
        binding?.success
    }
    private var input = NewCheckListInput()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewCheckListFragmentBinding.inflate(inflater, container, false)
        setup()
        return binding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setup() {
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        vm.state.observe(viewLifecycleOwner, handleState)
    }

    private fun setupListeners() {
        normalLayout?.checkListNameInput?.doOnTextChanged { text, start, before, count ->
            input = input.copy(
                name= text.toString()
            )
        }
        normalLayout?.continueBtn?.setOnClickListener {
            vm.save(input)
        }
    }

    private val handleState = Observer<NewCheckListInputViewModel.State> { state ->
        when (state) {
            is NewCheckListInputViewModel.State.Success -> {
                binding?.viewFlipper?.displayedChild = SUCCESS
            }

            is NewCheckListInputViewModel.State.Error -> {

            }
            is NewCheckListInputViewModel.State.Loading -> {
                binding?.viewFlipper?.displayedChild = LOADING
            }

            is NewCheckListInputViewModel.State.Idle -> {
                binding?.viewFlipper?.displayedChild = NORMAL
            }
        }
    }
}
package com.example.searchapp_hw26.presentation.screen.search_page

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.searchapp_hw26.databinding.FragmentSearchPageBinding
import com.example.searchapp_hw26.presentation.base.BaseFragment
import com.example.searchapp_hw26.presentation.event.SearchPageEvent
import com.example.searchapp_hw26.presentation.extension.showToast
import com.example.searchapp_hw26.presentation.screen.search_page.adapter.SearchPageRecyclerView
import com.example.searchapp_hw26.presentation.state.SearchPageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class SearchPageFragment :
    BaseFragment<FragmentSearchPageBinding>(FragmentSearchPageBinding::inflate) {

    private val viewModel: SearchPageViewModel by viewModels()

    override fun setListeners() {
        binding.etCategories.addTextChangedListener(

            object : TextWatcher {

                var delay: Long = 1000
                var timer = Timer()

                override fun afterTextChanged(text: Editable?) {

                    timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            if (text!!.isNotBlank()) {
                                viewModel.onEvent(SearchPageEvent.SearchCategory(text.toString()))
                            }
                        }
                    }, delay)
                }

                override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    timer.cancel()
                    timer.purge()
                }
            })
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchPage.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(searchPageState: SearchPageState) = with(searchPageState) {
        binding.progressBar.root.visibility =
            if (isLoading) View.VISIBLE else View.GONE

        errorMessage?.let {
            binding.root.showToast(errorMessage)
            viewModel.onEvent(SearchPageEvent.ResetErrorMessage)
        }

        searchedCategory?.let {
            binding.rvCategories.adapter = SearchPageRecyclerView().apply {
                submitList(it)
            }
        }
    }
}
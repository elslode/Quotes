package com.elsloude.quotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.elsloude.quotes.common.State
import com.elsloude.quotes.databinding.FragmentQuotesBinding
import com.elsloude.quotes.presentation.adapter.QuoteAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding: FragmentQuotesBinding
        get() = _binding ?: throw RuntimeException("FragmentQuotesBinding is null")

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.openConnection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = QuoteAdapter(requireContext())
        binding.quotesRv.adapter = adapter
        binding.quotesRv.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        binding.quotesRv.animation = null

        lifecycleScope.launch {
            viewModel.getQuotes()

            viewModel.quotesFlow.collect {
                Log.d("onViewCreated", "onViewCreated: $it")
                when (it) {
                    is State.Error -> {
                        binding.progressBar.isVisible = false
                    }
                    State.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is State.Success -> {
                        binding.progressBar.isVisible = false
                        adapter.submitList(it.data)
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.closeConnection()
    }
}
package com.elsloude.quotes.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.elsloude.quotes.App
import com.elsloude.quotes.common.State
import com.elsloude.quotes.databinding.FragmentQuotesBinding
import com.elsloude.quotes.di.ViewModelFactory
import com.elsloude.quotes.presentation.adapter.QuoteAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding: FragmentQuotesBinding
        get() = _binding ?: throw RuntimeException("FragmentQuotesBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: QuotesViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[QuotesViewModel::class.java]
        return binding.root
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
            viewModel.quotesFlow.collect {
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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.closeConnection()
    }
}
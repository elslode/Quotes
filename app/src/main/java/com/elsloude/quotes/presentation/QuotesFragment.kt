package com.elsloude.quotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elsloude.quotes.common.State
import com.elsloude.quotes.databinding.FragmentQuotesBinding
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

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getQuotes()

            viewModel.quotesFlow.collect {
                when(it) {
                    is State.Error -> {

                    }
                    State.Loading -> {

                    }
                    is State.Success -> {
                        Log.d("onViewCreated", "onViewCreated: ${it.data}")
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
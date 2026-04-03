package com.example.nasa.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa.R
import com.example.nasa.databinding.MainFragmentBinding
import com.example.nasa.ui.main.options.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment(), SpaceClickListener {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter by lazy { MainAdapter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mainRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenItem.collect { items ->
                    mainAdapter.items = items
                }
            }
        }
    }

    override fun onSearchClick(query: String) {
        findNavController().navigate(
            resId = R.id.action_mainFragment_to_detailsFragment,
            args = bundleOf(
                "QUERY" to query,
                "IS_FAV" to false
            )
        )
    }

    override fun onFavoriteClick() {
        findNavController().navigate(
            resId = R.id.action_mainFragment_to_detailsFragment,
            args = bundleOf(
                "IS_FAV" to true
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
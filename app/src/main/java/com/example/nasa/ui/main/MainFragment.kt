package com.example.nasa.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa.R
import com.example.nasa.databinding.MainFragmentBinding
import com.example.nasa.ui.main.options.MainAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(), SpaceClickListener {

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter by lazy { MainAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.mainRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeViewModel() {
        viewModel.screenItem.observe(viewLifecycleOwner) { items ->
            mainAdapter.submitList(items)
        }
    }

    override fun onSearchQueryChange(query: String) {
        val bundle = bundleOf("search_query" to query)
        findNavController().navigate(
            R.id.action_mainFragment_to_detailsFragment,
            bundle
        )
    }

    override fun onFavoriteClick() {
        findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
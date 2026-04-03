package com.example.nasa.ui.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa.databinding.DetailsFragmentBinding
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.details.ScreenType
import com.example.nasa.ui.details.bottom.sheet.DetailsBottomSheet
import com.example.nasa.ui.details.fragment.item.DetailsClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(), DetailsClickListener {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private val detailsAdapter by lazy {
        DetailsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.gridRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailsAdapter
        }

        setupDataObservation()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupDataObservation() {
        val query = arguments?.getString("QUERY") ?: ""
        val isFav = arguments?.getBoolean("IS_FAV") ?: false

        val screenType = if (isFav) ScreenType.Favorites else ScreenType.RemoteSearch(query)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPhoto(screenType).collectLatest { pagingData ->
                detailsAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDetailClick(photo: UISpaceData) {

        val sheet = DetailsBottomSheet().apply {
            arguments = Bundle().apply {
                putParcelable("SPACE_DATA", photo)
            }
        }
        sheet.show(childFragmentManager, "DetailsBottomSheet")

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
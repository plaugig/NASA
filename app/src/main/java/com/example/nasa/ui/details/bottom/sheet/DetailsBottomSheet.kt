package com.example.nasa.ui.details.bottom.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.nasa.R
import com.example.nasa.databinding.BottomSheetDetailsBinding
import com.example.nasa.ui.UISpaceData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsBottomSheet: BottomSheetDialogFragment() {

    private var _binding: BottomSheetDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val photo = arguments?.let {
            BundleCompat.getParcelable(
                it,
                "SPACE_DATA",
                UISpaceData:: class.java
            )?: return
        }

        if (photo != null) {
            setupStaticUI(photo)
            favoriteLogic(photo)
        } else {
            dismiss()
        }

    }

    fun setupStaticUI(photo: UISpaceData){
        binding.title.text = photo.title
        binding.location.text = photo.location
        binding.description.text = photo.description
        binding.author.text = photo.photographer
        binding.date.text = photo.date

        Glide.with(this).load(photo.imageUrl).into(binding.image)
    }

    fun favoriteLogic(photo: UISpaceData){
        viewModel.isFavorite(photo.nasaId).observe(viewLifecycleOwner) { isLiked ->
            val icon = if (isLiked){
                R.drawable.ic_heart_red
            } else {
                R.drawable.is_heart
            }
            binding.likeBtn.setImageResource(icon)

            binding.likeBtn.setOnClickListener {
                viewModel.toggleFavorite(photo)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
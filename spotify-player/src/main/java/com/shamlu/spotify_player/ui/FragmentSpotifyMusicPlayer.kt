package com.shamlu.spotify_player.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shamlu.common.BaseFragment
import com.shamlu.common.ViewModelBase
import com.shamlu.common.extentions.setToColorsWithOffset
import com.shamlu.spotify_player.R
import com.shamlu.spotify_player.databinding.FragmentSpotifyMusicPlayerBinding
import org.koin.android.viewmodel.ext.android.viewModel


class FragmentSpotifyMusicPlayer : BaseFragment() {

    private val viewModel: ViewModelSpotifyMusicPlayer by viewModel()
    override fun getViewModel(): ViewModelBase = viewModel
    private lateinit var binding: FragmentSpotifyMusicPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpotifyMusicPlayerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomSheet()
        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.playerLiveData.observe(viewLifecycleOwner , Observer {


        })
    }

    private fun setupBottomSheet() {

        val behavior = BottomSheetBehavior.from(binding.includedViewBottomSheetMusicPlayerContent.viewBottomSheetBackGround)
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) = viewModel.setBottomSheetState(newState)

            override fun onSlide(bottomSheet: View, slideOffset: Float) = viewModel.setBottomSheetOffset(slideOffset)
        })


    }

}
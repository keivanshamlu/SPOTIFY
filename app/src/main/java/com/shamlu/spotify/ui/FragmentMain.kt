package com.shamlu.spotify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shamlu.common.BaseFragment
import com.shamlu.common.ViewModelBase
import com.shamlu.spotify.databinding.FragmentMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentMain : BaseFragment() {



    private val viewModel : ViewModelMain by viewModel()
    override fun getViewModel(): ViewModelBase = viewModel
    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentMainBinding.inflate(inflater , container , false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
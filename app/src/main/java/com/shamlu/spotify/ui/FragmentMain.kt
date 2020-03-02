package com.shamlu.spotify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shamlu.common.BaseFragment
import com.shamlu.common.ViewModelBase
import com.shamlu.spotify.databinding.FragmentMainBinding
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentMain : BaseFragment() {


    private val viewModel : ViewModelMain by viewModel()
    override fun getViewModel(): ViewModelBase = viewModel
    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater , container , false).apply {
            this.viewModel = viewModel
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
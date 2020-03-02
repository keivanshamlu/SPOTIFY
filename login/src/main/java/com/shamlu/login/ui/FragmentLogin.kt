package com.shamlu.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shamlu.common.BaseFragment
import com.shamlu.common.ViewModelBase
import com.shamlu.login.databinding.FragmentLoginBinding
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentLogin : BaseFragment() {

    private val viewModel : ViewModelLogin by viewModel()
    override fun getViewModel(): ViewModelBase = viewModel
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater , container , false).apply {
            this.viewModel = viewModel
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
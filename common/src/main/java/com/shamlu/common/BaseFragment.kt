package com.shamlu.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shamlu.navigation.model.To
import com.shamlu.navigation.model.Back

abstract class BaseFragment : Fragment() {
    abstract fun getViewModel(): ViewModelBase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().navigation.observe(this, Observer {
            it.get()?.also {
                when (it) {
                    is To -> findNavController().navigate(it.direction, it.options)
                    is Back -> findNavController().popBackStack()
                }
            }
        })
    }
}
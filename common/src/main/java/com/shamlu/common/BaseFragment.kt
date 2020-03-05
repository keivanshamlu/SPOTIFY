package com.shamlu.common

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shamlu.navigation.NavigationCommand
import com.shamlu.navigation.model.To
import com.shamlu.navigation.model.Back

abstract class BaseFragment : Fragment() {
    abstract fun getViewModel(): ViewModelBase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().navigation.observe(this, Observer {
            it?.getContentIfNotHandled()?.also { command ->
                try {
                    when (command) {
                        is NavigationCommand.To -> {
                            findNavController().navigate(command.directions, command.options)
                        }
                        is NavigationCommand.Back -> {
                            findNavController().navigateUp()
                        }
                    }
                }catch (e: Exception){
                    Log.e("FragmentBase Mini app", "navigation Error: $e")
                }
            }
        })
    }
}
package com.shamlu.common.base

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.shamlu.common.R
import com.shamlu.common.ViewModelBase
import com.shamlu.common.extension.setupSnackBar
import com.shamlu.navigation.NavigationCommand


abstract class DialogBottomSheetBase : BottomSheetDialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigation(getViewModel())
        setupSnackBar(this, getViewModel().snackBarError, Snackbar.LENGTH_LONG)
        dialog!!.window!!.attributes.windowAnimations = R.style.BottomSheetDialogSlideAnimation

    }

    override fun onResume() {
        super.onResume()
        val window: Window = dialog!!.window!!
        val size = Point()
// Store dimensions of the screen in size
        val display: Display = window.windowManager.defaultDisplay
        display.getSize(size)
// Set the width of the dialog proportional to 97% of the screen width
        window.setLayout((size.x * 1), WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.BOTTOM)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
// Call super onResume after sizing
        super.onResume()
    }

    abstract fun getViewModel(): ViewModelBase

    // UTILS METHODS ---

    /**
     * Observe a [NavigationCommand] [Event] [LiveData].
     * When this [LiveData] is updated, [Fragment] will navigate to its destination
     */
    private fun observeNavigation(viewModel: ViewModelBase) {
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { command ->
                try {
                    when (command) {
                        is NavigationCommand.To -> findNavController().navigate(command.directions, command.options)
                        is NavigationCommand.Back -> findNavController().navigateUp()
                    }

                } catch (e: Exception) {
                    Log.e("FragmentBase Mini app", "navigation Error: $e")
                }
            }
        })
    }


    /**
     * [FragmentNavigatorExtras] mainly used to enable Shared Element transition
     */
    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

}
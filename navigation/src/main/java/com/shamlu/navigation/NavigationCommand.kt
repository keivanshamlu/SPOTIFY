package com.shamlu.navigation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

/**
 * A simple sealed class to handle more properly
 * navigation from a [ViewModel]
 */
sealed class NavigationCommand {
    data class To(val directions: NavDirections, val options: NavOptions? = null, val closeKeyboard: Boolean = true) : NavigationCommand()
    object Back: NavigationCommand()
}

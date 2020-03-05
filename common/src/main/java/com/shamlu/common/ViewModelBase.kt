package com.shamlu.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.shamlu.common.util.ErrorModel
import com.shamlu.common.util.Event
import com.shamlu.navigation.NavigationCommand
import com.shamlu.navigation.model.NavigationDirection


abstract class ViewModelBase : ViewModel() {
     // FOR ERROR HANDLER
    protected val _snackbarError = MutableLiveData<Event<ErrorModel>>()
    val snackBarError: LiveData<Event<ErrorModel>> get() = _snackbarError

    // FOR NAVIGATION
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    /**
     * Convenient method to handle navigation from a [ViewModel]
     */
    fun navigate(directions: NavDirections, options: NavOptions? = null) {
        _navigation.value = Event(NavigationCommand.To(directions, options))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }
}
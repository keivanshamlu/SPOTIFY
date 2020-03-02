package com.shamlu.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamlu.navigation.model.NavigationDirection


abstract class ViewModelBase : ViewModel() {
    private val _navigation: MutableLiveData<Switch<NavigationDirection>> = MutableLiveData()
    val navigation = _navigation

    fun navigate(directions: NavigationDirection) {
        _navigation.value = Switch.nullSwitch(directions)
    }
}
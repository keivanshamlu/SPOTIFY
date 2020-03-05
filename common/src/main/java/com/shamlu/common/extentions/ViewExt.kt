package com.shamlu.common.extentions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.shamlu.common.R
import com.shamlu.common.util.ErrorModel
import com.shamlu.common.util.Event

/**
 * Transforms static java function SnackBar.make() to an extension function on View.
 */
fun Fragment.showSnackBar(snackBarText: String, timeLength: Int, isNetworkConnection: Boolean = false) {
    activity?.let { Snackbar.make(it.findViewById<View>(android.R.id.content), snackBarText, timeLength)
            .apply {

                if (isNetworkConnection) {
                    setAction(getString(R.string.snack_bar_settings)) { context.startDeviceSettings() }
                    setActionTextColor(ContextCompat.getColor(context, R.color.black))
                    setText(getString(R.string.error_network_check))
                }
            }.show() }
}

/**
 * Triggers a snackBar message when the value contained by snackBarTaskMessageLiveEvent is modified.
 */
fun Fragment.setupSnackBar(lifecycleOwner: LifecycleOwner, snackBarEvent: LiveData<Event<ErrorModel>>, timeLength: Int) {
    snackBarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.also { res ->
            res.intError?.let { error -> context?.let { showSnackBar(it.getString(error), timeLength, res.isNetworkError) } }
            res.stringError?.let { error -> showSnackBar(error, timeLength, res.isNetworkError) }
        }
    })
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm?.hideSoftInputFromWindow(this.getWindowToken(), 0)
}
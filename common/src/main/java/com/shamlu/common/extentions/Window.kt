package com.shamlu.common.extentions

import android.view.Window
import android.view.WindowManager

fun Window.showKeyboard() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
}

fun Window.hideKeyboard() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}
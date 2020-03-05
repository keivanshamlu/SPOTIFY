package com.shamlu.common.extentions

import com.google.android.material.tabs.TabLayout
import android.util.DisplayMetrics
import android.view.ViewGroup


fun TabLayout.adjustMe() {
    var totalTabsWidth = 0

    (0..tabCount).map {
        val tabView = (getChildAt(0) as ViewGroup).getChildAt(it)
        if (tabView != null)
            totalTabsWidth += tabView.measuredWidth + tabView.paddingLeft + tabView.paddingRight
    }

    if (totalTabsWidth <= displayMetrics().widthPixels) {

        tabMode = TabLayout.MODE_FIXED
        tabGravity = TabLayout.GRAVITY_FILL

    } else {
        tabMode = TabLayout.MODE_SCROLLABLE
    }

}

private fun TabLayout.displayMetrics(): DisplayMetrics {
    val displayMetrics = context.resources.displayMetrics
    display.getRealMetrics(displayMetrics)
    return displayMetrics
}

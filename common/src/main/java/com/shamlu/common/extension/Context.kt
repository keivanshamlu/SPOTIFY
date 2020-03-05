package com.shamlu.common.extension

import android.content.Context
import android.util.TypedValue

fun Context.px(dp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this.resources.displayMetrics)
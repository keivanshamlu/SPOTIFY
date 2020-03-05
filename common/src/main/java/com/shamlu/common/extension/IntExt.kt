package com.shamlu.common.extension

import android.graphics.Color
import java.text.NumberFormat
import java.util.*

val Int.toColor: Int
    get() = Color.parseColor(String.format("#%06X", (0xFFFFFF and this)))

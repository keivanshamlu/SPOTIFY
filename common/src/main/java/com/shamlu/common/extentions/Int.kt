package com.shamlu.common.extentions

import android.graphics.Color
import java.text.NumberFormat
import java.util.*

val Int.toColor: Int
    get() = Color.parseColor(String.format("#%06X", (0xFFFFFF and this)))

val String.toColor: Int
    get() = Color.parseColor(this)


val Int.toPersian: String
    get() = String.format(Locale("fa"), "%d", this)

fun Int.currencyFormat(formatter: NumberFormat): String = formatter.format(this)
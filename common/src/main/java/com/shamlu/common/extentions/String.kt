package com.shamlu.common.extentions

import android.content.Context
import android.graphics.Typeface
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import java.text.NumberFormat
import java.util.*

val String.en: String
    get() = arrayOf(
            Pair("۱", "1"),
            Pair("۲", "2"),
            Pair("۳", "3"),
            Pair("۴", "4"),
            Pair("۵", "5"),
            Pair("۶", "6"),
            Pair("۷", "7"),
            Pair("۸", "8"),
            Pair("۹", "9"),
            Pair("۰", "0")
    ).forEach {
        this.replace(it.first, it.second)
    }.let { this }



fun String.spanColor(color: Int) = SpannableString(this).apply {
    setSpan(
            ForegroundColorSpan(color), 0, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}
package com.shamlu.common.extentions

import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.shamlu.common.R
import java.text.NumberFormat
import java.util.*

fun TextView.setBoldText(targetText: String, vararg textsToBold: String) {

    val styleBoldSpan = StyleSpan(Typeface.BOLD)

    val spanBuilder = SpanBuilder()

    targetText.split(" ").forEach {
        if (textsToBold.contains(it))
            spanBuilder.appendWithSpace(it, styleBoldSpan)
        else
            spanBuilder.appendWithSpace(it)
    }

    text = spanBuilder.build()
}

fun TextView.setBoldTexts(targetText: String, bolds: List<String>) {
    text = SpannableStringBuilder().setBoldTexts(targetText, bolds)
}

fun SpannableStringBuilder.setBoldTexts(targetText: String, bolds: List<String>) = this.apply {
    var lastIndex = 0
    bolds.forEach {
        targetText.substring(lastIndex, targetText.length).indexOf(it).takeIf { it > -1 }?.also { index ->
            append(targetText.substring(lastIndex, index + lastIndex))
            append(SpannableString(it).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, it.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            })
            lastIndex += index + it.length
        }
    }
    append(targetText.substring(lastIndex))
}

fun TextView.setBoldTextUsingHtml(targetText: String, vararg textsToBold: String) {

    var text : String = targetText
    textsToBold.forEach {
        text = text.replace(it, "<mainTickets>${it}</mainTickets>")
    }

    setHtml(text)
}

fun TextView.setHtml(text : String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT))
    else
        setText(Html.fromHtml(text))
}


fun TextView.addDrawable(resId: Int) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(context, resId), null)
}

fun TextView.setStyleText(targetText: String, styleText: List<StyleText>) {
    text = SpannableStringBuilder().apply {
        var lastIndex = 0
        styleText.forEach {styleText ->
            targetText.substring(lastIndex, targetText.length).indexOf(styleText.text).takeIf { it > -1 }?.also { index ->
                append(targetText.substring(lastIndex, index + lastIndex))
                append(SpannableString(styleText.text).apply {
                    setSpan(styleText.style, 0, styleText.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                })
                lastIndex += index + styleText.text.length
            }
        }
        append(targetText.substring(lastIndex))
    }
}


data class StyleText(
        val text: String,
        val style: Any
)
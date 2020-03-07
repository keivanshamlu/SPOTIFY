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
import android.util.TypedValue
import android.view.View
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
fun TextView.setTextColorsWithOffset(colorFrom : Int, colorTo : Int, offset : Float){
    this.setTextColor(
        interpolateColor(
            offset,
            colorFrom, colorTo
        )
    )
}

fun View.setAlphaWithOffsetToGone(offset: Float){

    this.alpha = 1 - offset
}
fun View.setAlphaWithOffsetToVisible(offset: Float){

    this.alpha =  offset
}

fun TextView.setToSizeWithOffset(sizeFrom : Float, sizeTo : Float, offset : Float){

    var finalSize = sizeFrom + offset * (sizeTo.minus(sizeFrom))

    this.setTextSize(TypedValue.COMPLEX_UNIT_PX , finalSize)
}


// Helper method to interpolate colors
private fun interpolateColor(fraction: Float, startValue: Int, endValue: Int): Int {
    val startA = startValue shr 24 and 0xff
    val startR = startValue shr 16 and 0xff
    val startG = startValue shr 8 and 0xff
    val startB = startValue and 0xff
    val endA = endValue shr 24 and 0xff
    val endR = endValue shr 16 and 0xff
    val endG = endValue shr 8 and 0xff
    val endB = endValue and 0xff
    return startA + (fraction * (endA - startA)).toInt() shl 24 or
            (startR + (fraction * (endR - startR)).toInt() shl 16) or
            (startG + (fraction * (endG - startG)).toInt() shl 8) or
            startB + (fraction * (endB - startB)).toInt()
}

data class StyleText(
        val text: String,
        val style: Any
)
package com.shamlu.common.extension

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.TextView


fun TextView.setStyleText(targetText: String, texts: List<StyleText>) {
    text = SpannableStringBuilder().apply {
        var lastIndex = 0
        texts.forEach {styleText ->
            targetText.substring(lastIndex, targetText.length).indexOf(styleText.text).also { index ->
                append(targetText.substring(lastIndex, index + lastIndex))
                append(SpannableString(styleText.text).apply {
                    setSpan(styleText.style(), 0, styleText.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                })
                lastIndex += index + styleText.text.length
            }
        }
        append(targetText.substring(lastIndex))
    }
}


abstract class StyleText(val text: String) {
    abstract fun style(): Any
}

class Bold(boldText: String) : StyleText(boldText) {
    override fun style() = StyleSpan(Typeface.BOLD)
}

class Italic(italicText: String) : StyleText(italicText) {
    override fun style() = StyleSpan(Typeface.ITALIC)
}

class ItalicBold(italicBoldText: String) : StyleText(italicBoldText) {
    override fun style() = StyleSpan(Typeface.BOLD_ITALIC)
}

class Underline(underLineText: String) : StyleText(underLineText) {
    override fun style() = UnderlineSpan()
}

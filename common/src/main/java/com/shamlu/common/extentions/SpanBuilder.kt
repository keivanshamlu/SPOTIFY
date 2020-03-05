package com.shamlu.common.extentions

import android.text.SpannableStringBuilder
import android.text.style.CharacterStyle
import android.text.Spannable


class SpanBuilder {

    private val spanSections: MutableList<SpanSection> = ArrayList()
    private val stringBuilder: StringBuilder = StringBuilder()

    private inner class SpanSection (private val text: String, private val startIndex: Int, vararg styles: CharacterStyle) {
        private val styles: Array<CharacterStyle> = styles as Array<CharacterStyle>

        fun apply(spanStringBuilder: SpannableStringBuilder?) {
            if (spanStringBuilder == null) return
            for (style in styles) {
                spanStringBuilder.setSpan(style, startIndex, startIndex + text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
        }
    }

    fun append(text: String, vararg styles: CharacterStyle): SpanBuilder {
        if (styles.isNotEmpty()) {
            spanSections.add(SpanSection(text, stringBuilder.length, *styles))
        }
        stringBuilder.append(text)
        return this
    }

    fun appendWithSpace(text: String, vararg styles: CharacterStyle): SpanBuilder {
        return append(text + " ", *styles)
    }

    fun appendWithLineBreak(text: String, vararg styles: CharacterStyle): SpanBuilder {
        return append(text + "\n", *styles)
    }

    fun build(): SpannableStringBuilder {
        val ssb = SpannableStringBuilder(stringBuilder.toString())
        for (section in spanSections) {
            section.apply(ssb)
        }
        return ssb
    }

    override fun toString(): String {
        return stringBuilder.toString()
    }
}
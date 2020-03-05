package com.shamlu.common.extentions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun Drawable.setTintX(@ColorRes colorRes: Int, context: Context): Drawable {
    val drawable = DrawableCompat.wrap(this)
    DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorRes))
    return drawable
}
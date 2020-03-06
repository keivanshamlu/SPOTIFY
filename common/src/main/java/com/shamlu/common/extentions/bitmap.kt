package com.shamlu.common.extentions

import android.graphics.Bitmap
import androidx.palette.graphics.Palette
import com.google.android.material.appbar.AppBarLayout

fun Bitmap.createPalleteSync() : Palette = Palette.from(this).generate()

package com.shamlu.common.base

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView



    class ItemDecorationGridView(val context: Context, private val spacingDp: Int, private val mGridSize: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

            val resources = context.resources
            val spacingPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacingDp.toFloat(), resources.displayMetrics)

            val bit = if (spacingPx > mGridSize) Math.round(spacingPx / mGridSize) else 1
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition

            val rowPosition = itemPosition % mGridSize
            outRect.left = if(rowPosition == 0) spacingDp else 0
            outRect.right = if(rowPosition == 0) 0 else spacingDp

        }
    }
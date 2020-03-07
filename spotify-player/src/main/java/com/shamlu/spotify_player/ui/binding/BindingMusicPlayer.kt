package com.shamlu.spotify_player.ui.binding

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shamlu.app.android.domain.model.player.BottomSheetState
import com.shamlu.common.extentions.*
import com.shamlu.spotify_player.R
import com.shamlu.spotify_player.ui.dataState.PlayerDetails

@BindingAdapter("binding:trackName" , "binding:artistName" , requireAll = true)
fun setTrackNames(textView: TextView, trackName:String? , artistName : String?) {

    textView.setText(trackName + " - " + artistName)
}

@BindingAdapter("binding:imageBackgroundBitmap" )
fun setImageBackgroundBitmap(imageView: ImageView, bitmap: Bitmap?) {
    Glide.with(imageView).load(bitmap).apply(RequestOptions.circleCropTransform()).into(imageView)
}


@BindingAdapter("binding:bottomSheetState" , "binding:playerState" )
fun setBottomSheetAndPlayerState(view: View, bottomSheetState: BottomSheetState? , playerState: PlayerDetails?) {

    bottomSheetState?.let {

        view.setToColorsWithOffset(
            ContextCompat.getColor(view.context, R.color.white),
            playerState?.variant?: ContextCompat.getColor(view.context!!, R.color.white),
            bottomSheetState.offset?:0f
        )

    }
}


@BindingAdapter("binding:bottomSheetState" , "binding:playerState" )
fun settextViewBottomSheetAndPlayerState(textView: TextView, bottomSheetState: BottomSheetState? , playerState: PlayerDetails?) {


    textView.setAlphaWithOffsetToGone(bottomSheetState?.offset?.times(3) ?:0f)
}
@BindingAdapter("binding:bottomSheetStateExpanded" , "binding:playerStateExpanded" )
fun settextViewExpandedBottomSheetAndPlayerState(textView: TextView, bottomSheetState: BottomSheetState? , playerState: PlayerDetails?) {

    bottomSheetState?.offset?.let {
        val newOffset =  bottomSheetState.offset!!.minus(0.4)

        if(it > 0 ){
            textView.setAlphaWithOffsetToVisible(newOffset.toFloat())
            textView.setTextColorsWithOffset(ContextCompat.getColor(textView.context , R.color.white) , playerState?.textColor ?:ContextCompat.getColor(textView.context , R.color.black),
                bottomSheetState.offset?:0f)
        }
        else textView.setAlphaWithOffsetToVisible(0f)

    }


}

@BindingAdapter("binding:bottomSheetStateForImageview" , "binding:playerState" , requireAll = true)
fun setBottomSheetOffset(imageView: ImageView , offset: Float , playerState: PlayerDetails?){


    imageView.setRotation(offset * 180)
    imageView.setToColorFilterWithOffset(
        ContextCompat.getColor(imageView.context, R.color.black),
        playerState?.textColor?:ContextCompat.getColor(imageView.context, R.color.black),
        offset
    )

}

@BindingAdapter("binding:offsetMarginTop")
fun setBottomSheetOffset(view: View , offset: Float ){


    view.setMarginTop(20 , offset)
}

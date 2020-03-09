package com.shamlu.spotify_player.ui.binding

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shamlu.app.android.domain.model.player.BottomSheetState
import com.shamlu.common.extentions.*
import com.shamlu.spotify_player.ui.dataState.PlayerDetails
import com.spotify.protocol.types.PlayerState
import android.graphics.drawable.BitmapDrawable



@BindingAdapter("binding:trackName" , "binding:artistName" , requireAll = true)
fun setTrackNames(textView: TextView, trackName:String? , artistName : String?) {

    textView.setText(trackName + " - " + artistName)
}

@BindingAdapter("binding:imageBackgroundBitmap" )
fun setImageBackgroundBitmap(imageView: ImageView, bitmap: Bitmap?) {


    bitmap?.let {
        Glide.with(imageView).load(it).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

}


@BindingAdapter("binding:bottomSheetState" , "binding:playerState" )
fun setBottomSheetAndPlayerState(view: View, bottomSheetState: BottomSheetState? , playerState: PlayerDetails?) {

    bottomSheetState?.let {

        view.setToColorsWithOffset(
            ContextCompat.getColor(view.context, com.shamlu.spotify_player.R.color.white),
            playerState?.variant?: ContextCompat.getColor(view.context!!, com.shamlu.spotify_player.R.color.white),
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

    val offset = bottomSheetState?.offset ?: 0f

        if(offset.minus(0.4) > 0 ){
            textView.setAlphaWithOffsetToVisible(offset)
            textView.setTextColorsWithOffset(ContextCompat.getColor(textView.context , com.shamlu.spotify_player.R.color.white) , playerState?.buttonsAndTextsColor ?:ContextCompat.getColor(textView.context , com.shamlu.spotify_player.R.color.black), offset)
        }
        else textView.setAlphaWithOffsetToVisible(0f)

    }
@BindingAdapter("binding:playerStateForIcons" )
fun setPlayerStateForIcons(imageView: ImageView , playerState: PlayerDetails?) {

    playerState?.let {imageView.background.setColorFilter(playerState.variant , PorterDuff.Mode.SRC_ATOP) }


}

@BindingAdapter("binding:bottomSheetStateForImageview" , "binding:playerState" , requireAll = true)
fun setBottomSheetOffset(imageView: ImageView , offset: Float , playerState: PlayerDetails?){


    imageView.setRotation(offset * 180)
    imageView.setToColorFilterWithOffset(
        ContextCompat.getColor(
            imageView.context,
            com.shamlu.spotify_player.R.color.black
        ),
        playerState?.buttonsAndTextsColor ?: ContextCompat.getColor(imageView.context, com.shamlu.spotify_player.R.color.black),
        offset
    )

}

@BindingAdapter("binding:playPauseData" , "binding:playPauseDetails")
fun setPlayPauseData(imageView: ImageView , playerState: PlayerState? , playerDetails: PlayerDetails?){

    playerState?.let {

        imageView.background = if(playerState.isPaused)ContextCompat.getDrawable(imageView.context , com.shamlu.spotify_player.R.drawable.ic_play_button) else ContextCompat.getDrawable(imageView.context , com.shamlu.spotify_player.R.drawable.ic_pause_button)
        imageView.background.setColorFilter(playerDetails?.variant ?:ContextCompat.getColor(imageView.context, com.shamlu.spotify_player.R.color.black), PorterDuff.Mode.SRC_ATOP)

    }
}



@BindingAdapter("binding:offsetMarginTop")
fun setBottomSheetOffset(view: View , offset: Float ){


    view.setMarginTop(20 , offset)
}

@BindingAdapter("bindin:spotifyPlayerButtonsColors")
fun setSpotifyPlayeButtonsColors(view : View , playerState: PlayerDetails?){

    view.background.setColorFilter(playerState?.buttonsAndTextsColor ?:ContextCompat.getColor(view.context, com.shamlu.spotify_player.R.color.black), PorterDuff.Mode.SRC_ATOP)}

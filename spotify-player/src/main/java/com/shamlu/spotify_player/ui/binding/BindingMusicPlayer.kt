package com.shamlu.spotify_player.ui.binding

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("binding:trackName" , "binding:artistName" , requireAll = true)
fun setTrackNames(textView: TextView, trackName:String? , artistName : String?) {

    textView.setText(trackName + " - " + artistName)
}

@BindingAdapter("binding:imageBackgroundBitmap" )
fun setImageBackgroundBitmap(imageView: ImageView, bitmap: Bitmap?) {

    Glide.with(imageView).load(bitmap).apply(RequestOptions.circleCropTransform()).into(imageView)
}

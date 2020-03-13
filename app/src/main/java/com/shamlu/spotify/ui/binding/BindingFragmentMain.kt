package com.shamlu.spotify.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shamlu.common.recources.apotify.SpotifyConnectionsResource

@BindingAdapter("binding:connectionState")
fun setConnectionState(textview: TextView , status: SpotifyConnectionsResource.Status?){

    when(status){
        SpotifyConnectionsResource.Status.CONNECTED -> {textview.setText("connected")}
        SpotifyConnectionsResource.Status.NOT_CONNECTED -> {textview.setText("disconnected")}
    }
}

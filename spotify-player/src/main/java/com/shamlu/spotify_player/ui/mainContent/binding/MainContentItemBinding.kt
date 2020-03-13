package com.shamlu.spotify_player.ui.mainContent.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.shamlu.spotify_player.R
import com.shamlu.spotify_player.ui.ViewModelSpotifyMusicPlayer
import com.spotify.android.appremote.api.ImagesApi
import com.spotify.protocol.types.ListItem


@BindingAdapter("binding:listItem" , "binding:playlistId" , "binding:currentPosition" , "binding:viewModel" ,"binding:itemsCount", requireAll = true)
fun setItemImage(imageView: ImageView,
                 listItem : ListItem,
                 playListId : String,
                 currentPosition: Int,
                 viewModel : ViewModelSpotifyMusicPlayer,
                 itemsCount : Int){

    viewModel.images.value?.get(playListId)?.get(currentPosition)?.let {
        Glide.with(imageView).load(it).into(imageView)
    }?: viewModel.imagesApi.let {

        Glide.with(imageView).load(R.drawable.shape_white_background).into(imageView)
        viewModel.imagesApi.value?.getImage(listItem.imageUri)?.setResultCallback {
            Glide.with(imageView).load(it).into(imageView)
            viewModel.setImage(playListId , it , currentPosition , itemsCount)
        }
    }

}
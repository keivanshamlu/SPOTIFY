package com.shamlu.spotify_player.ui

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.shamlu.spotify_player.ui.dataState.PlayerDetails
import com.shamlu.common.ViewModelBase
import com.shamlu.common.extentions.createPalleteSync
import com.shamlu.common.extentions.isDark
import com.shamlu.common.managers.SpotifyConnectionsManager
import com.shamlu.common.recources.apotify.SpotifyConnectionsResource
import com.spotify.protocol.types.Track

class ViewModelSpotifyMusicPlayer(private val connectionsManager: SpotifyConnectionsManager) : ViewModelBase() {



    private val _playerDetails = MutableLiveData<PlayerDetails>()
    val playerDetails : MutableLiveData<PlayerDetails> get() = _playerDetails


    val playerLiveData = Transformations.map(connectionsManager.spotifyConnection){
        it.takeIf { it.status == SpotifyConnectionsResource.Status.CONNECTED }
            ?.let { connectionResource ->
                connectionResource.data?.playerApi?.subscribeToPlayerState()?.setEventCallback { playerState ->
                        playerState.track?.let{
                            connectionResource.data?.imagesApi?.getImage(it.imageUri)?.setResultCallback { bitmap ->
                                bitmap?.createPalleteSync()?.getLightVibrantColor(Color.parseColor("#FFFFFF"))
                                handleSong(it , bitmap)
                                }
                        }

                    }

            }
    }

    private fun handleSong(track : Track , bitmap : Bitmap){

        var variantColor = bitmap.createPalleteSync().getDominantColor(Color.parseColor("#FFFFFF"))
        _playerDetails.value = PlayerDetails(
            variantColor,
            if (variantColor.isDark()) bitmap.createPalleteSync().getLightMutedColor(
                Color.parseColor(
                    "#FFFFFF"
                )
            )
            else bitmap.createPalleteSync().getDarkMutedColor(Color.parseColor("#000000")),
            track.artist.name,
            track.name,
            bitmap
        )

    }
}
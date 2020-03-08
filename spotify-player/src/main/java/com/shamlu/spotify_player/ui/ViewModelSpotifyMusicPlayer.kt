package com.shamlu.spotify_player.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.shamlu.app.android.domain.model.player.BottomSheetState
import com.shamlu.app.android.domain.model.player.BottomSheetStateEnum
import com.shamlu.spotify_player.ui.dataState.PlayerDetails
import com.shamlu.common.ViewModelBase
import com.shamlu.common.extentions.createPalleteSync
import com.shamlu.common.extentions.isDark
import com.shamlu.common.managers.SpotifyConnectionsManager
import com.shamlu.common.recources.apotify.SpotifyConnectionsResource
import com.spotify.protocol.types.PlayerState
import com.spotify.protocol.types.Track
import org.reactivestreams.Subscription

class ViewModelSpotifyMusicPlayer(private val connectionsManager: SpotifyConnectionsManager) : ViewModelBase() {



    private val _playerDetails = MutableLiveData<PlayerDetails>()
    val playerDetails : MutableLiveData<PlayerDetails> get() = _playerDetails

    private val _bottomSheetState = MutableLiveData<BottomSheetState>()
    val bottomSheetState : MutableLiveData<BottomSheetState> get() = _bottomSheetState


    private val _playerState = MutableLiveData<PlayerState>()
    val playerState : MutableLiveData<PlayerState> get() = _playerState



    val playerLiveData = Transformations.map(connectionsManager.spotifyConnection){
        it.takeIf { it.status == SpotifyConnectionsResource.Status.CONNECTED }
            ?.let { connectionResource ->
                connectionResource.data?.playerApi?.skipNext()
                connectionResource.data?.playerApi?.playerState
                connectionResource.data?.playerApi?.subscribeToPlayerState()?.setEventCallback { playerState ->

                    _playerState.value = playerState
                        playerState.track?.let{
                            connectionResource.data?.imagesApi?.getImage(it.imageUri)?.setResultCallback { bitmap ->
                                bitmap?.createPalleteSync()?.getLightVibrantColor(Color.parseColor("#FFFFFF"))
                                playerDetails.value?.image.takeIf { it != bitmap }.apply { handleSong(it , bitmap) }?:handleSong(it , bitmap)
                                }
                        }

                 }

            }
    }

    fun togglePlayPause(){

        connectionsManager.spotifyConnection.value.takeIf { it?.status == SpotifyConnectionsResource.Status.CONNECTED }.apply {

            this?.data?.let {

                if(playerState.value?.isPaused ?:false){

                    it.playerApi.resume()
                }else{

                    it.playerApi.pause()
                }
            }
        }

    }

    fun playNext(){

        connectionsManager.spotifyConnection.value.takeIf { it?.status == SpotifyConnectionsResource.Status.CONNECTED }.apply {

            this?.data?.let {
                    it.playerApi.skipNext()

            }
        }

    }
    fun playPrevious(){

        connectionsManager.spotifyConnection.value.takeIf { it?.status == SpotifyConnectionsResource.Status.CONNECTED }.apply {

            this?.data?.let {
                    it.playerApi.skipPrevious()

            }
        }

    }

    fun setBottomSheetState(state : Int){

        val lastValue = _bottomSheetState.value
        _bottomSheetState.value = lastValue?.copy(statusType = BottomSheetStateEnum.valuesOf(state))?: BottomSheetState(statusType = BottomSheetStateEnum.valuesOf(state))
    }

    fun setBottomSheetOffset(offset : Float){

        val lastValue = _bottomSheetState.value
        _bottomSheetState.value = lastValue?.copy(offset = offset)?:BottomSheetState(offset = offset)
    }

    private fun handleSong(track : Track , bitmap : Bitmap){

        val variant = bitmap.createPalleteSync().getDominantColor(Color.parseColor("#FFFFFF"))
        val mutedDark = bitmap.createPalleteSync().getDarkMutedColor(Color.parseColor("#FFFFFF"))
        val dark = bitmap.createPalleteSync().getDarkVibrantColor(Color.parseColor("#FFFFFF"))
        val mutedlight = bitmap.createPalleteSync().getDarkMutedColor(Color.parseColor("#FFFFFF"))

        val buttonsAndTextsColor = if(mutedDark!= 1 && mutedDark != variant) mutedDark else if(dark!= 1 && dark != variant)dark else mutedlight
        _playerDetails.value = PlayerDetails(
            variant,
            buttonsAndTextsColor,
            track.artist.name,
            track.name,
            bitmap
        )

    }
}
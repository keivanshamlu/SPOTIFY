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
import com.shamlu.app.android.domain.model.player.ContentTypeEnum
import com.shamlu.spotify_player.ui.dataState.PlayerDetails
import com.shamlu.common.ViewModelBase
import com.shamlu.common.extentions.createPalleteSync
import com.shamlu.common.extentions.isDark
import com.shamlu.common.managers.SpotifyConnectionsManager
import com.shamlu.common.recources.apotify.SpotifyConnectionsResource
import com.spotify.android.appremote.api.ContentApi
import com.spotify.android.appremote.api.ImagesApi
import com.spotify.android.appremote.api.PlayerApi
import com.spotify.protocol.client.CallResult
import com.spotify.protocol.types.*
import org.reactivestreams.Subscription

class ViewModelSpotifyMusicPlayer(private val connectionsManager: SpotifyConnectionsManager) :
    ViewModelBase() {


    private val _playerDetails = MutableLiveData<PlayerDetails>()
    val playerDetails: MutableLiveData<PlayerDetails> get() = _playerDetails

    private val _bottomSheetState = MutableLiveData<BottomSheetState>()
    val bottomSheetState: MutableLiveData<BottomSheetState> get() = _bottomSheetState

    private val _playerState = MutableLiveData<PlayerState>()
    val playerState: MutableLiveData<PlayerState> get() = _playerState

    private val _playerImage = MutableLiveData<Bitmap>()
    val playerImage: MutableLiveData<Bitmap> get() = _playerImage

    private val _listItems = MutableLiveData<ListItems>()
    val listItems : MutableLiveData<ListItems> get() = _listItems

    private val _imagesApi = MutableLiveData<ImagesApi>()
    val imagesApi : MutableLiveData<ImagesApi> get() = _imagesApi

    private val _contentApi = MutableLiveData<ContentApi>()
    val contentApi : MutableLiveData<ContentApi> get() = _contentApi

    private val _playerApi = MutableLiveData<PlayerApi>()
    val playerApi : MutableLiveData<PlayerApi> get() = _playerApi

    private val _images = MutableLiveData<MutableMap<String , MutableList<Bitmap?>>>()
    val images : MutableLiveData<MutableMap<String , MutableList<Bitmap?>>> get() = _images

    init {

        _images.value = hashMapOf()
    }

    val playerLiveData = Transformations.map(connectionsManager.spotifyConnection) {
        it.takeIf { it.status == SpotifyConnectionsResource.Status.CONNECTED }
            ?.let { connectionResource ->

                _imagesApi.value = connectionResource.data?.imagesApi
                _contentApi.value = connectionResource.data?.contentApi
                _playerApi.value = connectionResource.data?.playerApi
                connectionResource.data?.contentApi?.getRecommendedContentItems(ContentTypeEnum.DEFAULT.contentType)?.setResultCallback {items ->
                    listItems.value?.takeIf { !it.equals(items) }.apply {

                          _listItems.value = items
                    }
                }
                playerApi.value?.subscribeToPlayerState()
                    ?.setEventCallback { playerState ->

                        _playerState.value = playerState
                        playerState.track?.let {
                            imagesApi.value?.getImage(it.imageUri)
                                ?.setResultCallback { bitmap ->

                                    handleSong(it, bitmap)
                                }
                        }

                    }

            }
        it.takeIf { it.status == SpotifyConnectionsResource.Status.NOT_CONNECTED }?.let {
            navigateBack()
        }
    }

    fun setImage(playListId : String , image : Bitmap , imagePosition : Int , itemsCount : Int){

        val lastHash = _images.value

        val lastList = lastHash?.get(playListId)?: mutableListOf()
        lastList.takeIf { it.size == 0 }.apply {
            for (i in 0 .. itemsCount)this?.add(null)
        }
        lastList.add(imagePosition , image)

        lastHash?.put(playListId , lastList)

        _images.value = lastHash

    }

    fun togglePlayPause() {

        connectionsManager.spotifyConnection.value.takeIf { it?.status == SpotifyConnectionsResource.Status.CONNECTED }
            .apply {

                this?.data?.let {

                    if (playerState.value?.isPaused ?: false) {

                        it.playerApi.resume()
                    } else {

                        it.playerApi.pause()
                    }
                }
            }

    }

    fun playNext() {

        connectionsManager.spotifyConnection.value.takeIf { it?.status == SpotifyConnectionsResource.Status.CONNECTED }
            .apply {

                this?.data?.let {
                    it.playerApi.skipNext()

                }
            }

    }

    fun playPrevious() {

        connectionsManager.spotifyConnection.value.takeIf { it?.status == SpotifyConnectionsResource.Status.CONNECTED }
            .apply {

                this?.data?.let {
                    it.playerApi.skipPrevious()

                }
            }

    }

    fun setBottomSheetState(state: Int) {

        val lastValue = _bottomSheetState.value
        _bottomSheetState.value =
            lastValue?.copy(statusType = BottomSheetStateEnum.valuesOf(state)) ?: BottomSheetState(
                statusType = BottomSheetStateEnum.valuesOf(state)
            )
    }

    fun setBottomSheetOffset(offset: Float) {

        val lastValue = _bottomSheetState.value
        _bottomSheetState.value =
            lastValue?.copy(offset = offset) ?: BottomSheetState(offset = offset)
    }

    private fun handleSong(track: Track, bitmap: Bitmap) {

        val variant = bitmap.createPalleteSync().getDominantColor(Color.parseColor("#FFFFFF"))
        val mutedDark = bitmap.createPalleteSync().getDarkMutedColor(Color.parseColor("#FFFFFF"))
        val dark = bitmap.createPalleteSync().getDarkVibrantColor(Color.parseColor("#FFFFFF"))
        val mutedlight = bitmap.createPalleteSync().getDarkMutedColor(Color.parseColor("#FFFFFF"))

        if (track.uri != _playerDetails.value?.trackUri) _playerImage.value = bitmap

        val buttonsAndTextsColor =
            if (mutedDark != 1 && mutedDark != variant) mutedDark else if (dark != 1 && dark != variant) dark else mutedlight
        _playerDetails.value = PlayerDetails(
            variant,
            buttonsAndTextsColor,
            track.artist.name,
            track.name,
            track.uri
        )

    }

    fun playContnet(contentUri : String){


        playerApi.value?.play(contentUri)
    }

}

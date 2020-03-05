package com.shamlu.common.managers

import androidx.lifecycle.MutableLiveData
import com.shamlu.common.recources.apotify.SpotifyConnectionsResource
import com.spotify.android.appremote.api.SpotifyAppRemote

class SpotifyConnectionsManager {

    private val _spotifyConnection = MutableLiveData<SpotifyConnectionsResource<SpotifyAppRemote?>>()
    val spotifyConnection : MutableLiveData<SpotifyConnectionsResource<SpotifyAppRemote?>> get() = _spotifyConnection

    fun setConnectedToSpotify( spotifyAppRemote: SpotifyAppRemote?){

        _spotifyConnection.value = SpotifyConnectionsResource.connected(spotifyAppRemote)
    }

    fun setNotConnectedToSpotify(){

        _spotifyConnection.value = SpotifyConnectionsResource.notConnected()
    }
}
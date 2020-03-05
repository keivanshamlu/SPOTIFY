package com.shamlu.common.managers

import androidx.lifecycle.MutableLiveData
import com.shamlu.common.recources.apotify.SpotifyAuthResource
import com.shamlu.common.recources.apotify.SpotifyUser

class SessionManager {

    private val _spotifyUserSession = MutableLiveData<SpotifyAuthResource<SpotifyUser>>()
    val spotifyUserSession : MutableLiveData<SpotifyAuthResource<SpotifyUser>> get() = _spotifyUserSession


    fun setStateLoading(){

        _spotifyUserSession.value = SpotifyAuthResource.loading()
    }

    fun setStateLogin(token : String){


        _spotifyUserSession.value = SpotifyAuthResource.token(
            SpotifyUser(
                token
            )
        )
    }

    fun setStateLogOut(){

        _spotifyUserSession.value = SpotifyAuthResource.logOut()
    }



}
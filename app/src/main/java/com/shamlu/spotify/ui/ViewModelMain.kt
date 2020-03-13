package com.shamlu.spotify.ui

import androidx.lifecycle.Transformations
import com.shamlu.common.ViewModelBase
import com.shamlu.common.managers.SpotifyConnectionsManager
import com.shamlu.common.recources.apotify.SpotifyConnectionsResource
import javax.inject.Inject

class ViewModelMain(val connectionsManager: SpotifyConnectionsManager): ViewModelBase() {

    fun navigateToLoginFragment(){

        navigate(FragmentMainDirections.fragmentMainToDialogSpotifyMusicPlayer())
    }


}
package com.shamlu.spotify.ui

import com.shamlu.common.ViewModelBase
import javax.inject.Inject

class ViewModelMain: ViewModelBase() {

    fun navigateToLoginFragment(){

        navigate(FragmentMainDirections.fragmentMainToDialogSpotifyMusicPlayer())
    }
}
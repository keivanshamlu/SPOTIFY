package com.shamlu.spotify.di

import com.shamlu.common.di.moduleCommon
import com.shamlu.login.di.moduleLogin
import com.shamlu.spotify_player.di.moduleSpotifyMusicPlayer

val appComponent = listOf(
    moduleLogin,
    moduleMain,
    moduleCommon,
    moduleSpotifyMusicPlayer
)
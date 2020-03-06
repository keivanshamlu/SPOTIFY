package com.shamlu.spotify_player.di

import com.shamlu.spotify_player.ui.ViewModelSpotifyMusicPlayer
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleSpotifyMusicPlayer = module {

    viewModel { ViewModelSpotifyMusicPlayer(get()) }
}
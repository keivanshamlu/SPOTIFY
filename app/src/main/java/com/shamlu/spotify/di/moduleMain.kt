package com.shamlu.spotify.di

import com.shamlu.spotify.ui.ViewModelMain
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleMain = module {

    viewModel{ ViewModelMain() }
}
package com.shamlu.spotify.di

import com.shamlu.spotify.BuildConfig
import com.shamlu.spotify.ui.ViewModelMain
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleMain = module {

    viewModel{ ViewModelMain() }
    factory {
       val builder = AuthenticationRequest.Builder(
        BuildConfig.CLIENT_ID,
        AuthenticationResponse.Type.TOKEN,
        BuildConfig.REDIRECT_URI
    )
        builder.setScopes(arrayOf("streaming"))
        return@factory builder.build()
    }


}
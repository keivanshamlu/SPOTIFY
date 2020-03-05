package com.shamlu.common.di

import com.shamlu.common.managers.SessionManager
import com.shamlu.common.managers.SpotifyConnectionsManager
import org.koin.dsl.module

val moduleCommon = module {
    single { SessionManager() }
    single { SpotifyConnectionsManager() }
}
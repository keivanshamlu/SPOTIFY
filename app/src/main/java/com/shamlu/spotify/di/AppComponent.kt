package com.shamlu.spotify.di

import com.shamlu.common.di.moduleCommon
import com.shamlu.login.di.moduleLogin

val appComponent = listOf(
    moduleLogin,
    moduleMain,
    moduleCommon
)
package com.shamlu.login.di

import com.shamlu.login.ui.ViewModelLogin
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleLogin = module {

    viewModel { ViewModelLogin() }
}
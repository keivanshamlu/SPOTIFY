package com.example.minispotify


import android.app.Application
import com.shamlu.spotify.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.text.Typography.dagger


class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@BaseApplication)
            modules(
                    provideComponent()
            )

        }
    }
    open fun provideComponent() = appComponent

}
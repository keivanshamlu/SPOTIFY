package com.shamlu.spotify.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.KITKAT
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.shamlu.common.managers.SessionManager
import com.shamlu.common.managers.SpotifyConnectionsManager
import com.shamlu.common.recources.apotify.SpotifyConnectionsResource
import com.shamlu.login.ui.REQUEST_CODE_LOGIN_SPOTIFY
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private var mSpotifyAppRemote: SpotifyAppRemote? = null
    private val sessionManager by inject<SessionManager>()
    private val connectionsManager by inject<SpotifyConnectionsManager>()
    private val connectionParams by inject<ConnectionParams>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.shamlu.spotify.R.layout.activity_main)

        if (SDK_INT >= KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        observeManagers()
    }

    private fun observeManagers(){

        connectionsManager.spotifyConnection.observe(this , Observer {

            when(it.status){
                SpotifyConnectionsResource.Status.CONNECTED -> {}
                SpotifyConnectionsResource.Status.NOT_CONNECTED -> {}
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE_LOGIN_SPOTIFY) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
                // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                    sessionManager.setStateLogin(response.accessToken)
                }

                // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                    sessionManager.setStateLogOut()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onFailure(p0: Throwable?) {
                Log.wtf("MainActivity", "Failure!", p0)
                connectionsManager.setNotConnectedToSpotify()
            }

            override fun onConnected(p0: SpotifyAppRemote?) {
                mSpotifyAppRemote = p0
                Log.d("MainActivity", "Connected! Yay!")
                connectionsManager.setConnectedToSpotify(p0)
            }

        })
    }

    override fun onStop() {
        super.onStop()
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
    }

    override fun onSupportNavigateUp() =
        findNavController(this, com.shamlu.spotify.R.id.container_fragment).navigateUp()

}



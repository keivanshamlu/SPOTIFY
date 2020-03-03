package com.shamlu.spotify.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.shamlu.spotify.BuildConfig
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE
import android.content.Intent
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import kotlin.reflect.KProperty


class MainActivity : AppCompatActivity() {


    private val request by inject<AuthenticationRequest>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.shamlu.spotify.R.layout.activity_main)


        AuthenticationClient.openLoginActivity(this, REQUEST_CODE_LOGIN_SPOTIFY, request)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE_LOGIN_SPOTIFY) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
                // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                }

                // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                }
            }// Handle successful response
            // Handle error response
            // Most likely auth flow was cancelled
            // Handle other cases
        }
    }

    override fun onSupportNavigateUp() =
        findNavController(this, com.shamlu.spotify.R.id.container_fragment).navigateUp()

}

const val REQUEST_CODE_LOGIN_SPOTIFY = 1337


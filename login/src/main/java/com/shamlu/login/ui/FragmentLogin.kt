package com.shamlu.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.shamlu.common.BaseFragment
import com.shamlu.common.ViewModelBase
import com.shamlu.common.managers.SessionManager
import com.shamlu.common.recources.apotify.SpotifyAuthResource
import com.shamlu.login.databinding.FragmentLoginBinding
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentLogin : BaseFragment() {

    private val viewModel : ViewModelLogin by viewModel()
    override fun getViewModel(): ViewModelBase = viewModel
    private lateinit var binding : FragmentLoginBinding

    private val request by inject<AuthenticationRequest>()
    private val sessionManager by inject<SessionManager>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater , container , false).apply {
            this.viewModel = viewModel
        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeLogin()
        loginSpotify()
    }

    private fun loginSpotify(){

        sessionManager.setStateLogOut()
        AuthenticationClient.openLoginActivity(activity, REQUEST_CODE_LOGIN_SPOTIFY, request)

    }

    private fun observeLogin(){

        sessionManager.spotifyUserSession.observe(viewLifecycleOwner , Observer {

            when (it.status){
                SpotifyAuthResource.Status.TOKEN -> Toast.makeText(context!! , "logged in" , Toast.LENGTH_LONG).show()
            }
        })
    }

}
const val REQUEST_CODE_LOGIN_SPOTIFY = 1337
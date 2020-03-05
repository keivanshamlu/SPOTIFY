package com.shamlu.common.recources.apotify


data class SpotifyAuthResource<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> logOut(): SpotifyAuthResource<T> {
            return SpotifyAuthResource(
                Status.LOG_OUT,
                null
            )
        }


        fun <T> loading(): SpotifyAuthResource<T> {
            return SpotifyAuthResource(
                Status.LOADING,
                null
            )
        }
        fun <T> token(data: T?): SpotifyAuthResource<T> {
            return SpotifyAuthResource(
                Status.TOKEN,
                data
            )
        }


    }

    enum class Status {
        TOKEN,
        LOADING,
        LOG_OUT
    }
}

package com.shamlu.common.recources.apotify


data class SpotifyConnectionsResource<out T>(val status: Status, val data: T?) {

    companion object {
        fun <T> connected(data : T): SpotifyConnectionsResource<T> {
            return SpotifyConnectionsResource(
                Status.CONNECTED,
                data
            )
        }


        fun <T> notConnected(): SpotifyConnectionsResource<T> {
            return SpotifyConnectionsResource(
                Status.NOT_CONNECTED,
                null
            )
        }
    }

    enum class Status {
        CONNECTED,
        NOT_CONNECTED
    }
}

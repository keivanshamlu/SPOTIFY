package com.shamlu.spotify_player.ui.dataState

import android.graphics.Bitmap


data class PlayerDetails (
    var variant : Int,
    var textColor : Int,
    var artistName : String,
    var songName : String,
    var image : Bitmap
)
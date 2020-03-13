package com.shamlu.navigation.model.spotifyPlayer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NavModelSpotifyMainContentItem(
    val id: String?= null,
    val uri: String?= null,
    val imageUriRaw: String?= null,
    val title: String?= null,
    val subtitle: String?= null,
    val playable: Boolean,
    val hasChildren: Boolean
): Parcelable
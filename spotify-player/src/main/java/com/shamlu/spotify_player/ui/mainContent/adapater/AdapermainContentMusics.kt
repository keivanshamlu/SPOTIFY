package com.shamlu.spotify_player.ui.mainContent.adapater

import android.util.Log.v
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamlu.spotify_player.R
import com.shamlu.spotify_player.ui.ViewModelSpotifyMusicPlayer
import com.spotify.protocol.types.ListItem


class AdaptermainContentMusics(private val viewModel : ViewModelSpotifyMusicPlayer , private val playListId :String) : RecyclerView.Adapter<ViewHolderMainContentMusics>() {

    private val items: MutableList<ListItem> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderMainContentMusics(LayoutInflater.from(parent.context).inflate(
        R.layout.item_main_content_musics, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolderMainContentMusics, position: Int) =holder.bindTo(items[position], viewModel , playListId , position , items.size)
    // ---

    fun updateData(configResponses: List<ListItem>) {
        items.clear()
        items.addAll(configResponses)
        notifyDataSetChanged()
    }
}
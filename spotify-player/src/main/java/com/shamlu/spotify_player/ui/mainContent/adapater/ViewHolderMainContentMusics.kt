package com.shamlu.spotify_player.ui.mainContent.adapater

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shamlu.spotify_player.databinding.ItemMainContentMusicsBinding
import com.shamlu.spotify_player.ui.ViewModelSpotifyMusicPlayer
import com.spotify.protocol.types.ListItem

class ViewHolderMainContentMusics(parent: View) : RecyclerView.ViewHolder(parent) {

    private val binding = ItemMainContentMusicsBinding.bind(parent)

    fun bindTo(item : ListItem, viewModel: ViewModelSpotifyMusicPlayer , playListId : String , position : Int , itemsCount : Int) {
        binding.viewModel = viewModel
        binding.listItem = item
        binding.playListId = playListId
        binding.position = position
        binding.itemsCount = itemsCount
    }
}
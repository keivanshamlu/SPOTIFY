package com.shamlu.spotify_player.ui.mainContent.adapater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.shamlu.navigation.model.spotifyPlayer.NavModelSpotifyMainContentItem
import com.shamlu.spotify_player.ui.ViewModelSpotifyMusicPlayer
import com.shamlu.spotify_player.ui.mainContent.FragmentMainContentItem


class ViewPagerAdapterSpotifyPlayerMainContent(fm: FragmentManager, val items : List<NavModelSpotifyMainContentItem>, val viewModel :ViewModelSpotifyMusicPlayer) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = FragmentMainContentItem.newInstance(items.get(position) , viewModel , position)

    override fun getCount(): Int = items.count()

}

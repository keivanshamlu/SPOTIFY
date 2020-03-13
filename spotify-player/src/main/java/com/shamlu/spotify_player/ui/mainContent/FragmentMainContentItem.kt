package com.shamlu.spotify_player.ui.mainContent

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.shamlu.common.BaseFragment
import com.shamlu.common.ViewModelBase
import com.shamlu.common.base.ItemDecorationGridView
import com.shamlu.navigation.model.spotifyPlayer.NavModelSpotifyMainContentItem
import com.shamlu.spotify_player.R
import com.shamlu.spotify_player.databinding.FragmentMainContentItemBinding
import com.shamlu.spotify_player.ui.ViewModelSpotifyMusicPlayer
import com.shamlu.spotify_player.ui.mainContent.adapater.AdaptermainContentMusics
import com.spotify.protocol.types.ImageUri
import com.spotify.protocol.types.ListItem
import java.text.FieldPosition

class FragmentMainContentItem : BaseFragment() {


    override fun getViewModel(): ViewModelBase = viewModel!!
    private var viewModel: ViewModelSpotifyMusicPlayer? = null
    private lateinit var binding : FragmentMainContentItemBinding
    private lateinit var adapter : AdaptermainContentMusics
    private  var currentPosition: Int = 1
    private lateinit var item : ListItem
    var images : List<Bitmap> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainContentItemBinding.inflate(inflater , container , false)
        binding.viewModel = viewModel
        arguments.let {
            val navItem : NavModelSpotifyMainContentItem = it?.getParcelable("item")!!
            binding.item = navItem
            currentPosition = it.getInt("position")
            item = ListItem(navItem.id , navItem.uri , ImageUri(navItem.imageUriRaw) , navItem.title , navItem.subtitle , navItem.playable , navItem.hasChildren)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getCurrentItems()
    }

    private fun getCurrentItems(){



         viewModel?.contentApi?.value?.getChildrenOfItem(item , 100, 0)!!.setResultCallback {

             setUpRecyclerView(it.items.asList())
         }
    }

    private fun setUpRecyclerView(items : List<ListItem> ){

        adapter = AdaptermainContentMusics(viewModel!! , item.id)
        binding.recyclerViewMainContentMusics.layoutManager = GridLayoutManager(context!! , 2)
        binding.recyclerViewMainContentMusics.addItemDecoration(ItemDecorationGridView(context!! , resources.getDimension(
            R.dimen.dimen_14_dp).toInt() , 2))
        binding.recyclerViewMainContentMusics.adapter = adapter
        adapter.updateData(items)

    }

    companion object {

        fun newInstance(
            item : NavModelSpotifyMainContentItem,
            viewModel: ViewModelSpotifyMusicPlayer,
            position : Int
        ): FragmentMainContentItem =
            FragmentMainContentItem().apply {

                this.viewModel = viewModel
                arguments = Bundle().apply {
                    putParcelable("item" , item)
                    putInt("position" , position)
                }
            }
    }
}


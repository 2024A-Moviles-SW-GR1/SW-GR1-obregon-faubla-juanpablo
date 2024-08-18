package com.example.deber3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), AlbumAdapter.OnAlbumClickListener {
    private var recyclerViewAlbums: RecyclerView? = null
    private var recyclerViewSongs: RecyclerView? = null
    private var albumAdapter: AlbumAdapter? = null
    private var songAdapter: SongAdapter? = null

    private val albumList: MutableList<Album> = ArrayList()
    private val songList: MutableList<Song> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewAlbums = findViewById(R.id.recyclerViewAlbums)
        recyclerViewSongs = findViewById(R.id.recyclerViewSongs)

        recyclerViewAlbums?.let {
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            albumAdapter = AlbumAdapter(albumList, this)
            it.adapter = albumAdapter
        }

        recyclerViewSongs?.let {
            it.layoutManager = LinearLayoutManager(this)
            songAdapter = SongAdapter(songList)
            it.adapter = songAdapter
        }

        loadAlbums()
    }

    private fun loadAlbums() {
        albumList.add(Album("Brithish Steel", "https://a.allegroimg.com/s128/113cfe/0be6b48c47df99044eb581630473/CD-Judas-Priest-British-Steel"))
        albumList.add(Album("Painkiller", "https://a.allegroimg.com/s128/11bfdb/10399720405aa979153b3b1ac59e/JUDAS-PRIEST-PAINKILLER-CD"))
        albumList.add(Album("The dark side of the moon", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_AsHdR3VSISjHfQ0qQUipxOWuo6J1XupJFg&s"))
        albumList.add(Album("Iron Maiden (2015 Remaster)", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1jRmGkajza-ByzlUvQ-oDmumgWVq2ytQAUA&s"))
        albumList.add(Album("Roots", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtm5DG3w0Il58CopmyFhiqLiixYeNQ3djjsg&s"))
        albumList.add(Album("Bayou Country", "https://st.cdjapan.co.jp/pictures/s/08/31/UCCO-40044.jpg?v=1"))
        albumList.add(Album("Disraeli Gears", "https://st.cdjapan.co.jp/pictures/s/07/19/UIGY-9534.jpg?v=1"))
        albumList.add(Album("Black Caesar", "https://i.ebayimg.com/images/g/1fMAAOSwwfVl~Nwl/s-l64.png"))



        // Notificar al adaptador que los datos han cambiado
        albumAdapter!!.notifyDataSetChanged()
    }

    private fun loadSongsForAlbum(albumPosition: Int) {
        songList.clear()

        val albumImageUrl = albumList[albumPosition].imageUrl

        when (albumPosition) {
            0 -> {
                songList.add(Song("Rapid fire", albumImageUrl))
                songList.add(Song("Metal Gods", albumImageUrl))
                songList.add(Song("Breaking the law", albumImageUrl))
                songList.add(Song("Grinder", albumImageUrl))
                songList.add(Song("United", albumImageUrl))
                songList.add(Song("You don't have to be old to be wise", albumImageUrl))
                songList.add(Song("Living after midnight", albumImageUrl))
                songList.add(Song("The rage", albumImageUrl))
                songList.add(Song("Steeler", albumImageUrl))
                songList.add(Song("Red, white and blue", albumImageUrl))
            }
            1 -> {
                songList.add(Song("Painkiller", albumImageUrl))
                songList.add(Song("Hell Patrol", albumImageUrl))
                songList.add(Song("All Guns Blazing", albumImageUrl))
                songList.add(Song("Leather Rebel", albumImageUrl))
                songList.add(Song("Metal Meltdown", albumImageUrl))
                songList.add(Song("Night Crawler", albumImageUrl))
                songList.add(Song("Between the Hammer & the Anvil", albumImageUrl))
                songList.add(Song("A Touch of Evil", albumImageUrl))
                songList.add(Song("Battle Hymn", albumImageUrl))
                songList.add(Song("One Shot at Glory", albumImageUrl))
                songList.add(Song("Living Bad Dreams", albumImageUrl))
            }
            2 -> {
                songList.add(Song("Speak to me", albumImageUrl))
                songList.add(Song("Breathe (In the air)", albumImageUrl))
                songList.add(Song("On the run", albumImageUrl))
                songList.add(Song("Time", albumImageUrl))
                songList.add(Song("The great gig in the sky", albumImageUrl))
                songList.add(Song("Money", albumImageUrl))
                songList.add(Song("Us and them", albumImageUrl))
                songList.add(Song("Any color you like", albumImageUrl))
                songList.add(Song("Brain damage", albumImageUrl))
                songList.add(Song("Eclipse", albumImageUrl))
            }
            3 -> {
                songList.add(Song("Prowler", albumImageUrl))
                songList.add(Song("Remember tomorrow", albumImageUrl))
                songList.add(Song("Running free", albumImageUrl))
                songList.add(Song("Phantom of the opera", albumImageUrl))
                songList.add(Song("Transylvania", albumImageUrl))
                songList.add(Song("Strange world", albumImageUrl))
                songList.add(Song("Charlotte the harlot", albumImageUrl))
                songList.add(Song("Iron Maiden", albumImageUrl))
            }
            4 -> {
                songList.add(Song("Roots Bloody Roots", albumImageUrl))
                songList.add(Song("Attitude", albumImageUrl))
                songList.add(Song("Cut-Throat", albumImageUrl))
                songList.add(Song("Ratamahatta", albumImageUrl))
                songList.add(Song("Breed Apart", albumImageUrl))
                songList.add(Song("Straighthate", albumImageUrl))
                songList.add(Song("Spit", albumImageUrl))
                songList.add(Song("Lookaway", albumImageUrl))
                songList.add(Song("Dusted", albumImageUrl))
                songList.add(Song("Born Stubborn", albumImageUrl))
                songList.add(Song("Jasco", albumImageUrl))
                songList.add(Song("Itsari", albumImageUrl))
                songList.add(Song("Ambush", albumImageUrl))
                songList.add(Song("Endangered Species", albumImageUrl))
                songList.add(Song("Dictatorshit", albumImageUrl))
                songList.add(Song("Canyon Jam", albumImageUrl))
            }
            5 -> {
                songList.add(Song("Born on the Bayou", albumImageUrl))
                songList.add(Song("Bootleg", albumImageUrl))
                songList.add(Song("Graveyard Train", albumImageUrl))
                songList.add(Song("Good Golly Miss Molly", albumImageUrl))
                songList.add(Song("Penthouse Pauper", albumImageUrl))
                songList.add(Song("Proud Mary", albumImageUrl))
                songList.add(Song("Keep on Chooglin", albumImageUrl))
            }
            6 -> {
                songList.add(Song("Strange Brew", albumImageUrl))
                songList.add(Song("Sunshine of Your Love", albumImageUrl))
                songList.add(Song("World of Pain", albumImageUrl))
                songList.add(Song("Dance the Night Away", albumImageUrl))
                songList.add(Song("Blue Condition", albumImageUrl))
                songList.add(Song("Tales of Brave Ulysses", albumImageUrl))
                songList.add(Song("Swlabr", albumImageUrl))
                songList.add(Song("We're Going Wrong", albumImageUrl))
                songList.add(Song("Outside Women Blues", albumImageUrl))
                songList.add(Song("Take It Back", albumImageUrl))
                songList.add(Song("Mother's Lament", albumImageUrl))
            }
            7 -> {
                songList.add(Song("Down And Out in New YorK City", albumImageUrl))
                songList.add(Song("Blind Main Can See It", albumImageUrl))
                songList.add(Song("Sportin' Life", albumImageUrl))
                songList.add(Song("Dirty Harri", albumImageUrl))
                songList.add(Song("The boss", albumImageUrl))
                songList.add(Song("Make it good to yourself", albumImageUrl))
                songList.add(Song("Mama Feelgood", albumImageUrl))
                songList.add(Song("Like it is, like it was", albumImageUrl))
            }
        }

        songAdapter!!.notifyDataSetChanged()
    }

    override fun onAlbumClick(position: Int) {
        recyclerViewSongs?.visibility = View.VISIBLE
        loadSongsForAlbum(position)
    }
}
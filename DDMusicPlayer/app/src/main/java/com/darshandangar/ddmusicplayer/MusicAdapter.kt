package com.darshandangar.ddmusicplayer

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darshandangar.ddmusicplayer.databinding.MusicViewBinding

class MusicAdapter(private val context: Context, private var musiclist: ArrayList<Music>) : RecyclerView.Adapter<MusicAdapter.MyHolder>() {
    class MyHolder(binding: MusicViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.songNameMV
        val album = binding.songAlbumMV
        val image = binding.imageMV
        val duration = binding.songDuration
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(MusicViewBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.title.text = musiclist[position].title
        holder.album.text = musiclist[position].album
        holder.duration.text = formatDuration(musiclist[position].duration)
        Glide.with(context)
            .load(musiclist[position].artUri)
            .apply(RequestOptions().placeholder(R.drawable.ddmusic))
            .into(holder.image)
        holder.root.setOnClickListener {
            when{
                MainActivity.search -> sendIntent(ref = "MusicAdapterSearch",pos = position)
                musiclist[position].id == PlayerActivity.nowPlayingId ->
                    sendIntent(ref = "NowPlaying",pos = PlayerActivity.songPosition)
                else -> sendIntent(ref = "MusicAdapter",pos = position)
            }
//            sendIntent(ref = "MusicAdapter" ,pos = position)
        }
    }

    override fun getItemCount(): Int {
        return musiclist.size
    }
    fun updateMusicList(searchList: ArrayList<Music>){
        musiclist = ArrayList()
        musiclist.addAll(searchList)
        notifyDataSetChanged()
    }

    private fun sendIntent(ref: String,pos: Int){
        val intent = Intent(context,PlayerActivity::class.java)
        intent.putExtra("index",pos)
        intent.putExtra("class",ref)

        ContextCompat.startActivity(context,intent,null)
    }

}
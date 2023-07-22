package com.darshandangar.ddmusicplayer

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NotificationReceiver:BroadcastReceiver() {
    @SuppressLint("SupportAnnotationUsage")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(p0: Context?, p1: Intent?) {

        when(p1?.action){
            ApplicationClass.PREVIOUS -> prevNextSong(increment = false, context = p0!!)
            ApplicationClass.PLAY -> if (PlayerActivity.isPlaying) pauseMusic() else playMusic()
            ApplicationClass.NEXT -> prevNextSong(increment = true, context = p0!!)
            ApplicationClass.EXIT ->{
                exitApplication()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun playMusic(){
        PlayerActivity.isPlaying = true
        PlayerActivity.musicService!!.mediaPlayer!!.start()
        PlayerActivity.musicService!!.showNotification(R.drawable.pause_icon)
        PlayerActivity.binding.playpausebtnPA.setIconResource(R.drawable.pause_icon)
        NowPlaying.binding.playPausebtnNP.setIconResource(R.drawable.pause_icon)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun pauseMusic(){
        PlayerActivity.isPlaying = false
        PlayerActivity.musicService!!.mediaPlayer!!.pause()
        PlayerActivity.musicService!!.showNotification(R.drawable.play_icon)
        PlayerActivity.binding.playpausebtnPA.setIconResource(R.drawable.play_icon)
        NowPlaying.binding.playPausebtnNP.setIconResource(R.drawable.play_icon)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun prevNextSong(increment:Boolean, context: Context){
        setSongPosition(increment = increment)
        PlayerActivity.musicService!!.createMediaPlayer()
        Glide.with(context)
            .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.ddmusic))
            .into(PlayerActivity.binding.songImgPA)
        PlayerActivity.binding.songNamePA.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
        Glide.with(context)
            .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.ddmusic))
            .into(PlayerActivity.binding.songImgPA)
        NowPlaying.binding.songNameNP.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
        playMusic()
        PlayerActivity.fIndex = favouriteChecker(PlayerActivity.musicListPA[PlayerActivity.songPosition].id)
        if (PlayerActivity.isFavourite) PlayerActivity.binding.favouritebtnPA.setImageResource(R.drawable.favourite_pa_icon)
        else PlayerActivity.binding.favouritebtnPA.setImageResource(R.drawable.favorite_empty_icon)
    }


}
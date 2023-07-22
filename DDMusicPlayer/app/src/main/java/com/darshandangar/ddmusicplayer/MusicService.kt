package com.darshandangar.ddmusicplayer

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.*
import android.support.v4.media.session.MediaSessionCompat
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MusicService:Service() {
    private var myBinder = MyBinder()
    var mediaPlayer: MediaPlayer?= null
    private lateinit var mediaSession : MediaSessionCompat
    private lateinit var runnable: Runnable

    override fun onBind(p0: Intent?): IBinder {
        mediaSession = MediaSessionCompat(baseContext,"My Music")
        return myBinder
    }
    inner class MyBinder:Binder(){
        fun currentService(): MusicService {
            return this@MusicService
        }
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    fun showNotification(playPauseBtn: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val prevIntent = Intent(
                baseContext,
                NotificationReceiver::class.java
            ).setAction(ApplicationClass.PREVIOUS)

//            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                val prevPandingIntent: PendingIntent = FLAG
//            } else
//                0


            //val prevPandingintent: PendingIntent = PendingIntent.getBroadcast(


            val prevPandingintent = PendingIntent.getBroadcast(
                baseContext,
                0,
                prevIntent,
                PendingIntent.FLAG_IMMUTABLE

            )

            val playIntent = Intent(
                baseContext,
                NotificationReceiver::class.java
            ).setAction(ApplicationClass.PLAY)

            val playPandingintent = PendingIntent.getBroadcast(
                baseContext,
                0,
                playIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val nextIntent = Intent(
                baseContext,
                NotificationReceiver::class.java
            ).setAction(ApplicationClass.NEXT)

            val nextPandingintent = PendingIntent.getBroadcast(
                baseContext,
                0,
                nextIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val exitIntent = Intent(
                baseContext,
                NotificationReceiver::class.java
            ).setAction(ApplicationClass.EXIT)

            val exitPandingintent = PendingIntent.getBroadcast(
                baseContext,
                0,
                exitIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val imgArt = getImgArt(PlayerActivity.musicListPA[PlayerActivity.songPosition].path)
        val image = if (imgArt != null){
            BitmapFactory.decodeByteArray(imgArt,0,imgArt.size)
        }else{
            BitmapFactory.decodeResource(resources,R.drawable.dd_splash_screen)
        }


            val notification = NotificationCompat.Builder(baseContext,ApplicationClass.CHANNEL_ID)
                .setContentTitle(PlayerActivity.musicListPA[PlayerActivity.songPosition].title)
                .setContentText(PlayerActivity.musicListPA[PlayerActivity.songPosition].artist)
                .setSmallIcon(R.drawable.music_icon)
                .setLargeIcon(image)
                .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setOnlyAlertOnce(true)
                //.addAction(R.drawable.previous_icon,"Previous",prevIntent)
                .addAction(R.drawable.previous_icon,"Previous",prevPandingintent)
                .addAction(playPauseBtn,"Play",playPandingintent)
                .addAction(R.drawable.next_icon,"Next",nextPandingintent)
                .addAction(R.drawable.exit_icon,"Exit",exitPandingintent)

                .build()

            startForeground(17,notification)
        } else {
            Toast.makeText(baseContext,"Your Api lavel is Down please improve..",Toast.LENGTH_SHORT).show()
        }

    }

    fun createMediaPlayer(){
        try {
            if (PlayerActivity.musicService!!.mediaPlayer == null) PlayerActivity.musicService!!.mediaPlayer = MediaPlayer()
            PlayerActivity.musicService!!.mediaPlayer!!.reset()
            PlayerActivity.musicService!!.mediaPlayer!!.setDataSource(PlayerActivity.musicListPA[PlayerActivity.songPosition].path)
            PlayerActivity.musicService!!.mediaPlayer!!.prepare()

            PlayerActivity.binding.playpausebtnPA.setIconResource(R.drawable.pause_icon)
            PlayerActivity.musicService!!.showNotification(R.drawable.pause_icon)
            PlayerActivity.binding.tvSeekBarStart.text = formatDuration(mediaPlayer!!.currentPosition.toLong())
            PlayerActivity.binding.tvSeekBarEnd.text = formatDuration(mediaPlayer!!.duration.toLong())
            PlayerActivity.binding.seekBarPA.progress = 0
            PlayerActivity.binding.seekBarPA.max = mediaPlayer!!.duration
            PlayerActivity.nowPlayingId = PlayerActivity.musicListPA[PlayerActivity.songPosition].id
        } catch (e: Exception){
            return
        }
    }

    fun seekBarSetup(){
        runnable = Runnable {
            PlayerActivity.binding.tvSeekBarStart.text = formatDuration(mediaPlayer!!.currentPosition.toLong())
            PlayerActivity.binding.seekBarPA.progress = mediaPlayer!!.currentPosition
            Handler(Looper.getMainLooper()).postDelayed(runnable,200)


        }
        Handler(Looper.getMainLooper()).postDelayed(runnable,0)
    }


//    fun setSongPosition(increment: Boolean){
//        if (increment){
//            if (PlayerActivity.musicListPA.size - 1 == PlayerActivity.songPosition)
//                PlayerActivity.songPosition = 0
//            else ++PlayerActivity.songPosition
//        }else{
//            if (0 == PlayerActivity.songPosition)
//                PlayerActivity.songPosition = PlayerActivity.musicListPA.size-1
//            else --PlayerActivity.songPosition
//        }
//    }

}
package com.darshandangar.ddmusicplayer

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.media.MediaPlayer
import android.media.audiofx.AudioEffect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darshandangar.ddmusicplayer.databinding.ActivityPlayerBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

@Suppress("DEPRECATION")
class PlayerActivity : AppCompatActivity() ,ServiceConnection,MediaPlayer.OnCompletionListener{

    companion object{
        lateinit var musicListPA : ArrayList<Music>
        var songPosition: Int = 0
        var isPlaying:Boolean = false
        var musicService: MusicService? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var binding: ActivityPlayerBinding
        var repeat: Boolean = false
        var isFavourite: Boolean = false
        var fIndex: Int = -1
        var min15: Boolean = false
        var min30: Boolean = false
        var min60: Boolean = false
        var nowPlayingId: String = ""

    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPink)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeLayout()
        binding.playpausebtnPA.setOnClickListener {
            if (isPlaying) {
                pauseMusic()
            }
            else {playMusic()}
        }
        binding.prviousbtnPA.setOnClickListener {
            prevNextSong(increment = false)
        }
        binding.nextbtnPA.setOnClickListener {
            prevNextSong(increment = true)
        }
        binding.favouritebtnPA.setOnClickListener {
            if (!isFavourite){
                isFavourite = true
                binding.favouritebtnPA.setImageResource(R.drawable.favourite_pa_icon)
                FavouriteActivity.favouriteSongs.add(musicListPA[songPosition])
            } else{
                isFavourite = false
                binding.favouritebtnPA.setImageResource(R.drawable.favorite_empty_icon)

                FavouriteActivity.favouriteSongs.remove(musicListPA[songPosition])
                //FavouriteActivity.favouriteSongs.removeAt(findex)
            }
        }
        binding.seekBarPA.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) musicService!!.mediaPlayer!!.seekTo(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) = Unit

            override fun onStopTrackingTouch(p0: SeekBar?) = Unit
        })

        binding.repeatbtnPA.setOnClickListener {
            if (!repeat){
                repeat = true
                binding.repeatbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.light_red))
                Toast.makeText(this,"Repeat Mode On",Toast.LENGTH_SHORT).show()

            }else{
                repeat = false
                binding.repeatbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.pink))
                Toast.makeText(this,"Repeat Mode Off",Toast.LENGTH_SHORT).show()
            }
        }
        binding.backbtnPA.setOnClickListener {
            finish()
            //pauseMusic()
            //exitProcess(1)
        }
        binding.equalizerbtnPA.setOnClickListener {
            try {
                val eqIntent = Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL)
                eqIntent.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, musicService!!.mediaPlayer!!.audioSessionId)
                eqIntent.putExtra(AudioEffect.EXTRA_PACKAGE_NAME,baseContext.packageName)
                eqIntent.putExtra(AudioEffect.EXTRA_CONTENT_TYPE,AudioEffect.CONTENT_TYPE_MUSIC)
                startActivityForResult(eqIntent,17)
            } catch (e: Exception){
                Toast.makeText(this,"Equalizer Feature Not Supported Your Smartphone",Toast.LENGTH_SHORT).show()
            }
        }

        binding.timerbtnPA.setOnClickListener {
            val timer = min15 || min30 || min60
            if (!timer) showBottomSheetDialog()
            else{
                val builder = MaterialAlertDialogBuilder(this)
                builder.setTitle("Stop Timer")
                    .setMessage("Are you sure want to stop timer?")
                    .setPositiveButton("Yes"){_,_->
                        min15 = false
                        min30 = false
                        min60 = false
                        binding.timerbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.pink))


                    }
                    .setNegativeButton("No"){dialog,_ ->
                        dialog.dismiss()
                    }
                val customDialog = builder.create()
                customDialog.show()
                customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)
                customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE)
            }
        }

        binding.sharebtnPA.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "audio/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(musicListPA[songPosition].path))
            startActivity(Intent.createChooser(shareIntent,"Sharing Music File"))
        }

    }
    private fun setLayout(){
        fIndex = favouriteChecker(musicListPA[songPosition].id)
        Glide.with(this)
            .load(musicListPA[songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.ddmusic))
            .into(binding.songImgPA)
        binding.songNamePA.text = musicListPA[songPosition].title
        if (repeat) binding.repeatbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.light_red))
        if (min15 || min30 || min60) binding.timerbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.light_red))
        if (isFavourite) binding.favouritebtnPA.setImageResource(R.drawable.favourite_pa_icon)
        else binding.favouritebtnPA.setImageResource(R.drawable.favorite_empty_icon)
    }

    private fun createMediaPlayer(){
        try {
            if (musicService!!.mediaPlayer == null) musicService!!.mediaPlayer = MediaPlayer()
            musicService!!.mediaPlayer!!.reset()
            musicService!!.mediaPlayer!!.setDataSource(musicListPA[songPosition].path)
            musicService!!.mediaPlayer!!.prepare()
            musicService!!.mediaPlayer!!.start()
            isPlaying = true
            binding.playpausebtnPA.setIconResource(R.drawable.pause_icon)
            musicService!!.showNotification(R.drawable.pause_icon)
            binding.tvSeekBarStart.text = formatDuration(musicService!!.mediaPlayer!!.currentPosition.toLong())
            binding.tvSeekBarEnd.text = formatDuration(musicService!!.mediaPlayer!!.duration.toLong())
            binding.seekBarPA.progress = 0
            binding.seekBarPA.max = musicService!!.mediaPlayer!!.duration
            musicService!!.mediaPlayer!!.setOnCompletionListener(this)
            nowPlayingId = musicListPA[songPosition].id
        } catch (e: Exception){
            return
        }
    }

    private fun initializeLayout(){
        songPosition = intent.getIntExtra("index",0)
        when(intent.getStringExtra("class")){

            "FavouriteAdapter" -> {
                service()
                musicListPA = ArrayList()
                musicListPA.addAll(FavouriteActivity.favouriteSongs)
                setLayout()
            }

            "NowPlaying" -> {
                setLayout()
                binding.tvSeekBarStart.text = formatDuration(musicService!!.mediaPlayer!!.currentPosition.toLong())
                binding.tvSeekBarEnd.text = formatDuration(musicService!!.mediaPlayer!!.duration.toLong())
                binding.seekBarPA.progress = musicService!!.mediaPlayer!!.currentPosition
                binding.seekBarPA.max = musicService!!.mediaPlayer!!.duration
                if (isPlaying) binding.playpausebtnPA.setIconResource(R.drawable.pause_icon)
                else binding.playpausebtnPA.setIconResource(R.drawable.play_icon)
            }
            "MusicAdapterSearch" -> {
                service()
                musicListPA = ArrayList()
                musicListPA.addAll(MainActivity.musicListSearch)
                setLayout()
            }
            "MusicAdapter"->{
                service()
                musicListPA = ArrayList()
                musicListPA.addAll(MainActivity.MusicListMA)
                setLayout()
//                createMediaPlayer()


            }
            "MainActivity"->{
                service()
                musicListPA = ArrayList()
                musicListPA.addAll(MainActivity.MusicListMA)
                musicListPA.shuffle()
                setLayout()
//                createMediaPlayer()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun playMusic(){
        binding.playpausebtnPA.setIconResource(R.drawable.pause_icon)
        musicService!!.showNotification(R.drawable.pause_icon)
        isPlaying = true
        musicService!!.mediaPlayer!!.start()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun pauseMusic(){
        binding.playpausebtnPA.setIconResource(R.drawable.play_icon)
        musicService!!.showNotification(R.drawable.play_icon)
        isPlaying = false
        musicService!!.mediaPlayer!!.pause()
    }

    private fun prevNextSong(increment: Boolean)
    {
        if (increment){
            setSongPosition(increment = true)
            setLayout()
            createMediaPlayer()
        }else{
            setSongPosition(increment = false)
            setLayout()
            createMediaPlayer()
        }
    }



//    private fun favoutite(click: Boolean){
//        if (click)
//        {
//            binding.favouritebtnPA.setImageResource(R.drawable.favorite_icon)
//
//
//        }
//        else
//        {
//            binding.favouritebtnPA.setImageResource(R.drawable.favorite_empty_icon)
//
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
       val binder = p1 as MusicService.MyBinder
        musicService = binder.currentService()
        createMediaPlayer()
        musicService!!.seekBarSetup()

    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        musicService = null
    }

    override fun onCompletion(p0: MediaPlayer?) {
        setSongPosition(increment = true)
        createMediaPlayer()
        try {
            setLayout()
        }catch (e: Exception){ return }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 17 || resultCode == RESULT_OK)
            return
    }

    private fun showBottomSheetDialog(){
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_dialog)
        dialog.show()
        dialog.findViewById<LinearLayout>(R.id.min_15)?.setOnClickListener {
            Toast.makeText(this,"Music will stop after 15 minutes",Toast.LENGTH_SHORT).show()
            binding.timerbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.light_red))
            min15 = true
            Thread{Thread.sleep((15 * 60000).toLong())
            if (min15) exitApplication()}.start()
            dialog.dismiss()
        }
        dialog.findViewById<LinearLayout>(R.id.min_30)?.setOnClickListener {
            Toast.makeText(this,"Music will stop after 30 minutes",Toast.LENGTH_SHORT).show()
            binding.timerbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.light_red))
            min30 = true
            Thread{Thread.sleep((30 * 60000).toLong())
                if (min30) exitApplication()}.start()
            dialog.dismiss()
        }
        dialog.findViewById<LinearLayout>(R.id.min_60)?.setOnClickListener {
            Toast.makeText(this,"Music will stop after 60 minutes",Toast.LENGTH_SHORT).show()
            binding.timerbtnPA.setColorFilter(ContextCompat.getColor(this,R.color.light_red))
            min60 = true
            Thread{Thread.sleep((60 * 60000).toLong())
                if (min60) exitApplication()}.start()
            dialog.dismiss()
        }
    }

    private fun service(){
        //for Starting Service
        val intent = Intent(this,MusicService::class.java)
        bindService(intent,this, BIND_AUTO_CREATE)
        startService(intent)
    }
}
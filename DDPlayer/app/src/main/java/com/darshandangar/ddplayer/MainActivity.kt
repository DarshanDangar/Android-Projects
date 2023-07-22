package com.darshandangar.ddmusicplayer

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.darshandangar.ddmusicplayer.databinding.ActivityMainBinding
import java.io.File
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var musicAdapter: MusicAdapter

    companion object{
        lateinit var MusicListMA : ArrayList<Music>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeLayout()
        binding.shufflebtn.setOnClickListener {
            val intent = Intent(this@MainActivity,PlayerActivity::class.java)
            startActivity(intent)
        }

        binding.playlistbtn.setOnClickListener {
            val intent = Intent(this@MainActivity,PlaylistActivity::class.java)
            startActivity(intent)
        }
        binding.mainfavouritrbtn.setOnClickListener {
            val intent = Intent(this@MainActivity,FavouriteActivity::class.java)
            startActivity(intent)
        }
        binding.navview.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.navFeedback -> Toast.makeText(this,"Feedback",Toast.LENGTH_SHORT).show()
                R.id.navSetting -> Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
                R.id.navAbout -> Toast.makeText(this,"About",Toast.LENGTH_SHORT).show()
                R.id.navExit -> exitProcess(1)

            }
            true

        }
    }
    //for requesting permission
    private fun requestRuntimePermission(){
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),17)

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==17){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),17)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("SetTextI18n")
    private fun initializeLayout(){
        requestRuntimePermission()
        setTheme(R.style.coolPinkNav)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for nav drawer

        toggle = ActionBarDrawerToggle(this,binding.root,R.string.open,R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        MusicListMA = getAllAudio()
        binding.musicRV.setHasFixedSize(true)
        binding.musicRV.setItemViewCacheSize(17)
        binding.musicRV.layoutManager = LinearLayoutManager(this@MainActivity)
        musicAdapter = MusicAdapter(this@MainActivity, MusicListMA)
        binding.musicRV.adapter = musicAdapter
        binding.totalsong.text = "Total Song: "+musicAdapter.itemCount
    }









    @SuppressLint("Range")
    private fun getAllAudio():ArrayList<Music> {
        val tempList = ArrayList<Music>()
        val selection = Media.IS_MUSIC + "!= 0"
        val projection = arrayOf(
            Media._ID,
            Media.TITLE,
            Media.ALBUM,
            Media.ARTIST,
            Media.DURATION,
            Media.DATE_ADDED,
            Media.DATA,
            Media.ALBUM_ID
        )
        val cursor = this.contentResolver.query(
            Media.EXTERNAL_CONTENT_URI,projection,selection,null,
            Media.DATE_ADDED ,null)
        if (cursor != null)
            if (cursor.moveToFirst())
                do {
                    val titleC =
                        cursor.getString(cursor.getColumnIndex(Media.TITLE))
                    val idC = cursor.getString(cursor.getColumnIndex(Media._ID))
                    val albumC =
                        cursor.getString(cursor.getColumnIndex(Media.ALBUM))
                    val artistC =
                        cursor.getString(cursor.getColumnIndex(Media.ARTIST))
                    val pathC = cursor.getString(cursor.getColumnIndex(Media.DATA))
                    val durationC =
                        cursor.getLong(cursor.getColumnIndex(Media.DURATION))
                    val albumIdC = cursor.getLong(cursor.getColumnIndex(Media.ALBUM_ID)).toString()
                    val uri = Uri.parse("content://media/external/audio/albumart")
                    val artUriC = Uri.withAppendedPath(uri,albumIdC).toString()
                    val music = Music(
                        id = idC,
                        title = titleC,
                        album = albumC,
                        artist = artistC,
                        path = pathC,
                        duration = durationC,
                        artUri = artUriC
                    )
                    val file = File(music.path)
                    if (file.exists())
                        tempList.add(music)
                } while (cursor.moveToNext())
        cursor?.close()
        return tempList
    }

}
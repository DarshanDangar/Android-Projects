package com.darshandangar.ddmusicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darshandangar.ddmusicplayer.databinding.ActivityFavouriteBinding
import com.darshandangar.ddplayer.R

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPink)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
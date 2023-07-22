package com.darshandangar.ddmusicplayer

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.darshandangar.ddmusicplayer.databinding.ActivityPlaylistBinding

class PlaylistActivity() : AppCompatActivity(), Parcelable {
    private lateinit var binding: ActivityPlaylistBinding

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPink)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backbtnPL.setOnClickListener { finish() }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaylistActivity> {
        override fun createFromParcel(parcel: Parcel): PlaylistActivity {
            return PlaylistActivity(parcel)
        }

        override fun newArray(size: Int): Array<PlaylistActivity?> {
            return arrayOfNulls(size)
        }
    }
}
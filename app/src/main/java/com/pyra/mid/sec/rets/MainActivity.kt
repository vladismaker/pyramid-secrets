package com.pyra.mid.sec.rets

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSoundPyraMid()
    }

    private fun initSoundPyraMid(){
        soundPyraMid = MediaPlayer.create(this, R.raw.background_pyra_mid)
        soundPyraMid?.isLooping = true
    }

    override fun onPause() {
        super.onPause()

        soundPyraMid?.pause()
    }

    companion object{
        var soundPyraMid: MediaPlayer? = null
        var pauseSoundPyraMid:Boolean=false
    }

    override fun onResume() {
        super.onResume()

        soundPyraMid?.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        soundPyraMid?.release()
    }
}
package cn.example.androidProject.media

import android.content.res.AssetFileDescriptor
import android.widget.SeekBar

interface MediaService {

    fun callPlayerMusic(path: AssetFileDescriptor, seekBar: SeekBar)

    fun callPauseMusic()

    fun callConMusic()

    fun callResetMusic()

    fun callSeekToPos(pos: Int)


}
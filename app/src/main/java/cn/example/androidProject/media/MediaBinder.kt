package cn.example.androidProject.media

import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Bundle
import android.os.Message
import kotlin.concurrent.thread

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/media
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/6 0:14 星期六
 * @Description:
 **************************/
class MediaBinder : Binder(), MediaService {
    private val mediaPlayer = MediaPlayer()
    fun onBind(intent: Intent): MediaBinder = this

    override fun callPlayerMusic(path: String) {
        playMusic(path)
    }

    override fun callPauseMusic() {
        pauseMusic()
    }

    override fun callConMusic() {
        conMusic()
    }

    override fun callSeekToPos(pos: Int) {
        seekToPos(pos)
    }

    private fun playMusic(path: String) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.isLooping = true;
            mediaPlayer.prepare();
            mediaPlayer.start();
            updateSeekBar()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause();
        }
    }

    private fun conMusic() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start();
        }
    }

    private fun seekToPos(pos: Int) {
        mediaPlayer.seekTo(pos);
    }

    private fun updateSeekBar() {
        val duration: Int = mediaPlayer.duration
        thread {
            run {
                while (true) {
                    try {
                        Thread.sleep(1000)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    val currentPos = mediaPlayer.currentPosition
                    val message = Message.obtain()
                    val bundle = Bundle().apply {
                        putInt("duration", duration)
                        putInt("currentPos", currentPos)
                    }
                    message.data = bundle
                }
            }
        }
    }
}
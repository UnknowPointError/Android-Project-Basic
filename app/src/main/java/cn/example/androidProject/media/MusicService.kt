package cn.example.androidProject.media

import android.app.Service
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.SeekBar
import kotlin.concurrent.thread

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/media
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/6 0:14 星期六
 * @Description:
 **************************/
class MusicService : Service() {

    override fun onBind(p0: Intent?): IBinder {
        return MediaBinder()
    }

    class MediaBinder : Binder(), MediaService {

        private val mediaPlayer = MediaPlayer()

        override fun callPlayerMusic(path: AssetFileDescriptor, seekBar: SeekBar) {
            playMusic(path, seekBar)
        }

        override fun callPauseMusic() {
            pauseMusic()
        }

        override fun callConMusic() {
            conMusic()
        }

        override fun callResetMusic() {
            resetMusic()
        }

        override fun callSeekToPos(pos: Int) {
            seekToPos(pos)
        }

        private fun playMusic(path: AssetFileDescriptor, seekBar: SeekBar) {
            try {
                mediaPlayer.setDataSource(path.fileDescriptor, path.startOffset, path.length)
                mediaPlayer.isLooping = true
                mediaPlayer.prepare()
                mediaPlayer.start()
                updateSeekBar(seekBar)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun pauseMusic() {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        private fun conMusic() {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        private fun resetMusic() {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }

        private fun seekToPos(pos: Int) {
            mediaPlayer.seekTo(pos)
        }

        private fun updateSeekBar(seekBar: SeekBar) {
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
                        seekBar.max = duration
                        seekBar.progress = currentPos
                    }
                }
            }
        }
    }
}

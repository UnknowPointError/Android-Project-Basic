package cn.example.androidProject.media

import android.content.*
import android.media.MediaPlayer
import android.os.*
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import cn.example.androidProject.Util.isServiceRunning
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.Util.typename
import cn.example.androidProject.databinding.MediaActivityBinding
import android.os.Bundle


class MediaActivity : AppCompatActivity() {

    private val mBinding by lazy { MediaActivityBinding.inflate(layoutInflater) }
    private val mediaPlayer = MediaPlayer()
    private lateinit var isService: MediaService

    inner class MediaCon : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, service: IBinder?) {
            isService = service as (MediaService)
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initService()
        mBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                isService.callSeekToPos(seekBar.progress)
            }
        })
        // initMediaPlayer()
        // initComponent()

    }

    private fun initService() {
        // 开启服务
        val intent = Intent(this, MediaService::class.java)
        startService(intent)

        // 绑定服务
        val mediaCon = MediaCon()
        bindService(intent, mediaCon, BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("Sarcastic Sounds - Dead Dreams.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }


    private fun initComponent() {
        mBinding.apply {
            playMusic.setOnClickListener { mediaPlayer.apply { start().takeUnless { isPlaying } } }
            pauseMusic.setOnClickListener { mediaPlayer.apply { pause().takeIf { isPlaying } } }
            stopMusic.setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.reset()
                    initMediaPlayer()
                }
            }
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mediaPlayer.setOnMediaTimeDiscontinuityListener { mediaPlayer, timedText ->
                this.showToasts("${ mediaPlayer.metrics.getLong("android.media.mediaplayer.playingMs")}")
            }
        }*/
    }
}
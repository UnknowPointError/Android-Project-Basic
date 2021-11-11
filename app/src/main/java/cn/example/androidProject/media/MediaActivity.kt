package cn.example.androidProject.media

import android.content.*
import android.media.MediaPlayer
import android.os.*
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import cn.example.androidProject.databinding.MediaActivityBinding
import android.os.Bundle
import android.view.KeyEvent
import cn.example.androidProject.MyApplication


class MediaActivity : AppCompatActivity() {

    private val mBinding by lazy { MediaActivityBinding.inflate(layoutInflater) }
    private val mediaPlayer = MediaPlayer()
    private lateinit var isService: MusicService.MediaBinder
    private val mediaCon = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, service: IBinder?) {
            isService = service as (MusicService.MediaBinder)
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        initService()
        initComponent()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun initService() {
        val intent = Intent(this, MusicService::class.java)
        bindService(intent, mediaCon, Context.BIND_AUTO_CREATE)
    }

    private fun initComponent() {
        mBinding.apply {
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    isService.callSeekToPos(seekBar.progress)
                }
            })
            val assetManager = assets
            val fd = assetManager.openFd("Sarcastic Sounds - Dead Dreams.mp3")
            playMusic.setOnClickListener {
                isService.callPlayerMusic(fd, mBinding.seekBar)
                mediaTextView.text = "开始播放音乐"
            }
            pauseMusic.setOnClickListener {
                isService.callPauseMusic()
                mediaTextView.text = "已暂停音乐"
            }
            conMusic.setOnClickListener {
                isService.callConMusic()
                mediaTextView.text = "已继续音乐"
            }
            resetMusic.setOnClickListener {
                isService.callResetMusic()
                mediaTextView.text = "音乐尚未加载"
            }
        }
    }
}
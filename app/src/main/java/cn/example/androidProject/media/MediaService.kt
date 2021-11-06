package cn.example.androidProject.media

interface MediaService {

    fun callPlayerMusic(path: String)

    fun callPauseMusic()

    fun callConMusic()

    fun callSeekToPos(pos: Int)
}
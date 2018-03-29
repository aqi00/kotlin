package com.example.custom

import com.example.custom.service.MusicService

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_notify_service.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/17.
 */
class NotifyServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_service)
        var bPlay = false
        btn_send_service.setOnClickListener {
            bPlay = !bPlay
            val intent = intentFor<MusicService>("is_play" to bPlay,
                    "song" to et_song.text.toString())
            if (bPlay) {
                startService(intent)
                toast("歌曲${et_song.text}已在通知栏开始播放")
                btn_send_service.text = "停止播放音乐"
            } else {
                stopService(intent)
                toast("歌曲${et_song.text}已从通知栏清除")
                btn_send_service.text = "开始播放音乐"
            }
        }
    }

}

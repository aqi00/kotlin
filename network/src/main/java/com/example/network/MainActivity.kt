package com.example.network

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 * Created by ouyangshen on 2017/9/24.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_message.setOnClickListener { startActivity<MessageActivity>() }
        btn_progress_dialog.setOnClickListener { startActivity<ProgressDialogActivity>() }
        btn_progress_circle.setOnClickListener { startActivity<ProgressCircleActivity>() }
        btn_async_task.setOnClickListener { startActivity<AsyncTaskActivity>() }
        btn_json_parse.setOnClickListener { startActivity<JsonParseActivity>() }
        btn_json_convert.setOnClickListener { startActivity<JsonConvertActivity>() }
        btn_http_request.setOnClickListener { startActivity<HttpRequestActivity>() }
        btn_http_image.setOnClickListener { startActivity<HttpImageActivity>() }
        btn_download_apk.setOnClickListener { startActivity<DownloadApkActivity>() }
        btn_download_image.setOnClickListener { startActivity<DownloadImageActivity>() }
        btn_content_provider.setOnClickListener { startActivity<ContentProviderActivity>() }
        btn_content_resolver.setOnClickListener { startActivity<ContentResolverActivity>() }
        btn_content_observer.setOnClickListener { startActivity<ContentObserverActivity>() }
        btn_spannable.setOnClickListener { startActivity<SpannableActivity>() }
        btn_auto_update.setOnClickListener { startActivity<AutoUpdateActivity>() }
    }

}

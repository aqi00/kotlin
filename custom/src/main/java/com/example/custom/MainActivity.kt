package com.example.custom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 * Created by ouyangshen on 2017/9/17.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_custom_property.setOnClickListener { startActivity<CustomPropertyActivity>() }
        btn_measure_view.setOnClickListener { startActivity<MeasureViewActivity>() }
        btn_draw_round.setOnClickListener { startActivity<DrawRoundActivity>() }
        btn_runnable.setOnClickListener { startActivity<RunnableActivity>() }
        btn_progress_bar.setOnClickListener { startActivity<ProgressBarActivity>() }
        btn_text_progress.setOnClickListener { startActivity<TextProgressActivity>() }
        btn_progress_animation.setOnClickListener { startActivity<ProgressAnimationActivity>() }
        btn_notify_simple.setOnClickListener { startActivity<NotifySimpleActivity>() }
        btn_notify_counter.setOnClickListener { startActivity<NotifyCounterActivity>() }
        btn_notify_large.setOnClickListener { startActivity<NotifyLargeActivity>() }
        btn_notify_special.setOnClickListener { startActivity<NotifySpecialActivity>() }
        btn_notify_custom.setOnClickListener { startActivity<NotifyCustomActivity>() }
        btn_service_normal.setOnClickListener { startActivity<ServiceNormalActivity>() }
        btn_service_bind.setOnClickListener { startActivity<ServiceBindActivity>() }
        btn_notify_service.setOnClickListener { startActivity<NotifyServiceActivity>() }
        btn_vibrator.setOnClickListener { startActivity<VibratorActivity>() }
        btn_fresh_purchase.setOnClickListener { startActivity<FreshPurchaseActivity>() }
    }

}

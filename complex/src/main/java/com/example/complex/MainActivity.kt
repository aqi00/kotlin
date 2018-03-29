package com.example.complex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 * Created by ouyangshen on 2017/9/3.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_spinner_dialog.setOnClickListener { startActivity<SpinnerDialogActivity>() }
        btn_list_view.setOnClickListener { startActivity<ListViewActivity>() }
        btn_grid_view.setOnClickListener { startActivity<GridViewActivity>() }
        btn_recycler_linear.setOnClickListener { startActivity<RecyclerLinearActivity>() }
        btn_recycler_grid.setOnClickListener { startActivity<RecyclerGridActivity>() }
        btn_recycler_staggered.setOnClickListener { startActivity<RecyclerStaggeredActivity>() }
        btn_coordinator.setOnClickListener { startActivity<CoordinatorActivity>() }
        btn_tool_bar.setOnClickListener { startActivity<ToolbarActivity>() }
        btn_appbar_recycler.setOnClickListener { startActivity<AppbarRecyclerActivity>() }
        btn_appbar_nested.setOnClickListener { startActivity<AppbarNestedActivity>() }
        btn_collapse_pin.setOnClickListener { startActivity<CollapsePinActivity>() }
        btn_collapse_parallax.setOnClickListener { startActivity<CollapseParallaxActivity>() }
        btn_image_fade.setOnClickListener { startActivity<ImageFadeActivity>() }
        btn_scroll_flag.setOnClickListener { startActivity<ScrollFlagActivity>() }
        btn_scroll_alipay.setOnClickListener { startActivity<ScrollAlipayActivity>() }
        btn_view_pager.setOnClickListener { startActivity<ViewPagerActivity>() }
        btn_fragment_dynamic.setOnClickListener { startActivity<FragmentDynamicActivity>() }
        btn_tab_layout.setOnClickListener { startActivity<TabLayoutActivity>() }
        btn_broad_temp.setOnClickListener { startActivity<BroadTempActivity>() }
        btn_broad_system.setOnClickListener { startActivity<BroadSystemActivity>() }
        btn_swipe_recycler.setOnClickListener { startActivity<SwipeRecyclerActivity>() }
        btn_department_channel.setOnClickListener { startActivity<DepartmentChannelActivity>() }
    }

}

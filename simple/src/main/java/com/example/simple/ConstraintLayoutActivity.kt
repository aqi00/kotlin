package com.example.simple

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_constraint_layout.*
import org.jetbrains.anko.dip

/**
 * Created by ouyangshen on 2017/8/27.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
class ConstraintLayoutActivity : AppCompatActivity() {
    private var isMoved = false
    private var lastViewId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)
        lastViewId = cl_content.id
        btn_add_view.setOnClickListener { addNewView() }
        btn_move_hard.setOnClickListener { moveView() }
        btn_move_soft.setOnClickListener {
            //使用动画展示新旧约束关系的切换过程。如果删掉这行则不展示切换动画。该方法需要API19支持
            TransitionManager.beginDelayedTransition(cl_content)
            moveView()
        }
    }

    private fun addNewView() {
        val tv = TextView(this)
        tv.text = "长按删除该文本"
        val container = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        //设置控件左侧与另一个控件的左侧对齐
        //水平方向上只能使用start和end，因为left和right可能无法奏效
        container.startToStart = lastViewId
        //设置控件顶部与另一个控件的底部对齐
        container.topToBottom = lastViewId
        container.topMargin = dip(30)
        //左侧间距要使用Start，不能用Left，因为set.applyTo方法会清空Left的间距。marginStart需要API17支持
        container.marginStart = dip(10)
        tv.layoutParams = container
        tv.setOnLongClickListener { vv -> cl_content.removeView(vv); true }
        lastViewId += 1000
        tv.id = lastViewId
        cl_content.addView(tv)
    }

    private fun moveView() {
        val margin = dip((if (isMoved) 200 else 20).toFloat())
        //需要下载最新的constraint-layout，才能使用ConstraintSet
        val set = ConstraintSet()
        //复制原有的约束关系
        set.clone(cl_content)
        //清空该控件的约束关系
        //set.clear(tv_first.getId());
        //设置该控件的约束宽度
        //set.constrainWidth(tv_first.getId(), ConstraintLayout.LayoutParams.WRAP_CONTENT);
        //设置该控件的约束高度
        //set.constrainHeight(tv_first.getId(),ConstraintLayout.LayoutParams.WRAP_CONTENT);
        //设置该控件的顶部约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.TOP, cl_content.getId(), ConstraintSet.BOTTOM, margin);
        //设置该控件的底部约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.BOTTOM, cl_content.getId(), ConstraintSet.BOTTOM, margin);
        //设置该控件的左侧约束关系与间距
        set.connect(tv_first.id, ConstraintSet.START, cl_content.id, ConstraintSet.START, margin)
        //设置该控件的右侧约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.END, cl_content.getId(), ConstraintSet.END, margin);
        //LEFT和RIGHT的margin不管用，只有START和END的margin才管用
        //set.setMargin(tv_init.getId(), ConstraintSet.START, 200);
        //启用新的约束关系
        set.applyTo(cl_content)
        isMoved = !isMoved
    }

}

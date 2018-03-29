package com.example.custom.widget

import com.example.custom.R

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerTabStrip
import android.util.AttributeSet
import android.util.TypedValue

//自定义视图务必要在类名后面增加“@JvmOverloads constructor”，因为布局文件中的自定义视图必须兼容Java
class CustomPagerTab @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null) : PagerTabStrip(context, attrs) {
    private var txtColor = Color.BLACK
    private var textSize = 15
    init {
        //初始化时从attrs.xml读取CustomPagerTab的自定义属性
        if (attrs != null) {
            val attrArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomPagerTab)
            //从布局文件中获取新属性textColor的数值
            txtColor = attrArray.getColor(R.styleable.CustomPagerTab_textColor, txtColor)
            //从布局文件中获取新属性textSize的数值
            textSize = attrArray.getDimensionPixelSize(R.styleable.CustomPagerTab_textSize, textSize)
            attrArray.recycle()
        }
        //应用布局文件的textColor文本颜色
        setTextColor(txtColor)
        //应用布局文件的textSize文本大小
        setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
    }
}

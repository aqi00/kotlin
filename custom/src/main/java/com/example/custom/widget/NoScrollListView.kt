package com.example.custom.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

//自定义视图务必要在类名后面增加“@JvmOverloads constructor”，因为布局文件中的自定义视图必须兼容Java
class NoScrollListView @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyle: Int=0) : ListView(context, attrs, defStyle) {
    //将高度设为最大值，即所有项加起来的总高度
    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //注意位运算符的写法，按位右移在Kotlin中使用运算符shr来表达
        val expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}

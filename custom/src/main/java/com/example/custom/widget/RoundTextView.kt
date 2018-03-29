package com.example.custom.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Paint.Style
import android.util.AttributeSet
import android.widget.TextView

//自定义视图务必要在类名后面增加“@JvmOverloads constructor”，因为布局文件中的自定义视图必须兼容Java
class RoundTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyle: Int=0) : TextView(context, attrs, defStyle) {
    //控件只能重写onDraw方法
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //通过画笔Paint在画布Canvas上绘制图案
        val paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 2f
        paint.style = Style.STROKE
        paint.isAntiAlias = true
        val rectF = RectF(1f, 1f, (this.width - 1).toFloat(), (this.height - 1).toFloat())
        //方法drawRoundRect表示绘制圆角矩形
        canvas.drawRoundRect(rectF, 10f, 10f, paint)
    }
}

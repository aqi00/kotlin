package com.example.custom.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ProgressBar

//自定义视图务必要在类名后面增加“@JvmOverloads constructor”，因为布局文件中的自定义视图必须兼容Java
class TextProgressBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ProgressBar(context, attrs, defStyle) {
    var progressText = ""
    private var paint: Paint
    private var textColor = Color.WHITE
    private var textSize = 30f

    init {
        //初始化画笔
        paint = Paint()
        paint.color = textColor
        paint.textSize = textSize
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rect = Rect()
        //获得进度文本的矩形边界
        paint.getTextBounds(progressText, 0, progressText.length, rect)
        val x = width / 2 - rect.centerX()
        val y = height / 2 - rect.centerY()
        //把文本内容绘制在进度条的中间位置
        canvas.drawText(progressText, x.toFloat(), y.toFloat(), paint)
    }

}

package com.example.custom.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Paint.Style
import android.util.AttributeSet
import android.widget.LinearLayout

//自定义视图务必要在类名后面增加“@JvmOverloads constructor”，因为布局文件中的自定义视图必须兼容Java
class RoundLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyle: Int=0) : LinearLayout(context, attrs, defStyle) {

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        val paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 2f
        paint.style = Style.STROKE
        paint.isAntiAlias = true
        val rectF = RectF(1f, 1f, (this.width - 1).toFloat(), (this.height - 1).toFloat())
        canvas.drawRoundRect(rectF, 10f, 10f, paint)
    }
}

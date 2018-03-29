package com.example.network.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Paint.Style
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

//自定义视图务必要在类名后面增加“@JvmOverloads constructor”，因为布局文件中的自定义视图必须兼容Java
class TextProgressCircle @JvmOverloads constructor(private val mContext: Context, attr: AttributeSet? = null) : View(mContext, attr) {
    private val paintBack: Paint = Paint()
    private val paintFore: Paint = Paint()
    private val paintText: Paint = Paint()
    private var lineWidth = 10
    private var lineColor = Color.GREEN
    private var mTextSize = 50.0f
    private lateinit var mRect: RectF
    private var mProgress = 0

    init {
        //初始化背景画笔的相关属性
        paintBack.isAntiAlias = true
        paintBack.color = Color.LTGRAY
        paintBack.strokeWidth = lineWidth.toFloat()
        paintBack.style = Style.STROKE
        //初始化前景画笔的相关属性
        paintFore.isAntiAlias = true
        paintFore.color = lineColor
        paintFore.strokeWidth = lineWidth.toFloat()
        paintFore.style = Style.STROKE
        //初始化文本画笔的相关属性
        paintText.isAntiAlias = true
        paintText.color = Color.BLUE
        paintText.textSize = mTextSize
    }

    //重写onDraw绘图函数，绘制圆圈背景、圆圈前景，以及中央的进度文本
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = measuredWidth  //获得当前视图的丈量宽度
        val height = measuredHeight  //获得当前视图的丈量高度
        if (width <= 0 || height <= 0) {
            return
        }
        val diameter = Math.min(width, height)
        mRect = RectF(((width - diameter) / 2 + lineWidth).toFloat(), ((height - diameter) / 2 + lineWidth).toFloat(),
                ((width + diameter) / 2 - lineWidth).toFloat(), ((height + diameter) / 2 - lineWidth).toFloat())
        //绘制进度圆圈的背景，背景是完整的圆环(360度绘制)
        canvas.drawArc(mRect, 0f, 360f, false, paintBack)
        //绘制进度圆圈的前景，前景是实际进度占360度的百分比
        canvas.drawArc(mRect, 0f, (mProgress * 360 / 100).toFloat(), false, paintFore)
        val text = "${mProgress.toString()}%"
        val rect = Rect()
        //获得进度文本的矩形边界
        paintText.getTextBounds(text, 0, text.length, rect)
        val x = getWidth() / 2 - rect.centerX()
        val y = getHeight() / 2 - rect.centerY()
        //把文本内容绘制在进度圆圈的圆心位置
        canvas.drawText(text, x.toFloat(), y.toFloat(), paintText)
    }

    //设置进度数值以及进度文本的文字大小
    fun setProgress(progress: Int, textSize: Float) {
        mProgress = progress
        if (textSize > 0) {
            mTextSize = textSize
            paintText.textSize = mTextSize
        }
        invalidate()
    }

    //设置进度圆圈的线宽与颜色
    fun setProgressStyle(line_width: Int, line_color: Int) {
        if (line_width > 0) {
            lineWidth = line_width
            paintFore.strokeWidth = lineWidth.toFloat()
        }
        if (line_color > 0) {
            lineColor = line_color
            paintFore.color = lineColor
        }
        invalidate()
    }

}

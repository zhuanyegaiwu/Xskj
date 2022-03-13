package com.example.demo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF
import android.util.Log

class BatteryView : View {
    private val mColorLow by lazy {
        intArrayOf(
            Color.parseColor("#F85838"),
            Color.parseColor("#E11919")
        )
    }
    private val mColorMiddle by lazy {
        intArrayOf(
            Color.parseColor("#FB8F10"),
            Color.parseColor("#F75436"),
        )
    }
    private val mColorHight by lazy {
        intArrayOf(
            Color.parseColor("#1ADAF9"),
            Color.parseColor("#43DA92"),
        )
    }
    private val mPaint by lazy {
        Paint()
    }
    private val mPaintConver by lazy {
        Paint()
    }
    private lateinit var linearGradient: LinearGradient

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.setDither(true)
        mPaint.color = Color.WHITE
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 1.0F

        mPaintConver.style = Paint.Style.FILL_AND_STROKE
        mPaintConver.setDither(true)
        mPaintConver.color = Color.parseColor("#F4F5F7")
        mPaintConver.isAntiAlias = true
        mPaintConver.strokeWidth = 1.0F
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val height = DisplayTool.dip2px(context, measuredHeight/3.toFloat()).toFloat()
        val width = DisplayTool.dip2px(context, measuredWidth/3.toFloat()).toFloat()
        val path = Path()
        val pathCover = Path()
        when (value) {
            in 0F..20F -> {
                val mRecF = RectF(
                    DisplayTool.dip2px(context, 0F).toFloat(),
                    DisplayTool.dip2px(context, (height*3F/5F)).toFloat(),
                    DisplayTool.dip2px(context, width).toFloat(),
                    DisplayTool.dip2px(context, height).toFloat()
                )
                var startAngle = (90F - ((value * 9F / 2F)))
                var rotateAngle = (180F - ((20F - value) * 9F))

                path.addArc(mRecF, startAngle, rotateAngle)
            }
            in 21F..80F -> {
                path.addRect(
                    DisplayTool.dip2px(context, 0F).toFloat(),
                    DisplayTool.dip2px(context, ( height- value)).toFloat(),
                    DisplayTool.dip2px(context, width).toFloat(),
                    DisplayTool.dip2px(context, height*4F/5F).toFloat(),
                    Path.Direction.CW
                )
                val mRecF = RectF(
                    DisplayTool.dip2px(context, 0F).toFloat(),
                    DisplayTool.dip2px(context, (height*3F/5F)).toFloat(),
                    DisplayTool.dip2px(context, width).toFloat(),
                    DisplayTool.dip2px(context, height).toFloat()
                )
                path.addArc(mRecF, 0F, 180F)
            }
            in 81F..100F -> {
                path.addRect(
                    DisplayTool.dip2px(context, 0F).toFloat(),
                    DisplayTool.dip2px(context, height*1F/5F).toFloat(),
                    DisplayTool.dip2px(context, width).toFloat(),
                    DisplayTool.dip2px(context, height*4F/5F).toFloat(),
                    Path.Direction.CW
                )
                val mRecF = RectF(
                    DisplayTool.dip2px(context, 00F).toFloat(),
                    DisplayTool.dip2px(context, height*3F/5F).toFloat(),
                    DisplayTool.dip2px(context, width).toFloat(),
                    DisplayTool.dip2px(context, height).toFloat()
                )
                path.addArc(mRecF, 0F, 180F)

                val mRecFTop = RectF(
                    DisplayTool.dip2px(context, 00F).toFloat(),
                    DisplayTool.dip2px(context, 00F).toFloat(),
                    DisplayTool.dip2px(context, width).toFloat(),
                    DisplayTool.dip2px(context, height*2F/5F).toFloat()
                )
                path.addArc(mRecFTop, 180F, 180F)
                var rotateAngle = ((100F - value) * 9F)
                var startAngle = 270F - (rotateAngle / 2F)
                pathCover.addArc(mRecFTop, startAngle, rotateAngle)
            }
        }
        val locationbottom = 100.0F
        val locationtop = (100F - value)
        when (value) {
            in 0F..10F -> {
                linearGradient =
                    LinearGradient(
                        DisplayTool.dip2px(context, width/2F).toFloat(),
                        DisplayTool.dip2px(context, locationtop).toFloat(),
                        DisplayTool.dip2px(context, width/2F).toFloat(),
                        DisplayTool.dip2px(context, locationbottom).toFloat(),
                        mColorLow,
                        null,
                        Shader.TileMode.CLAMP
                    )
            }
            in 11F..50F -> {
                linearGradient =
                    LinearGradient(
                        DisplayTool.dip2px(context, width/2F).toFloat(),
                        DisplayTool.dip2px(context, locationtop).toFloat(),
                        DisplayTool.dip2px(context, width/2F).toFloat(),
                        DisplayTool.dip2px(context, locationbottom).toFloat(),
                        mColorMiddle,
                        null,
                        Shader.TileMode.CLAMP
                    )
            }
            else -> {
                linearGradient =
                    LinearGradient(
                        DisplayTool.dip2px(context, width/2F).toFloat(),
                        DisplayTool.dip2px(context, locationtop).toFloat(),
                        DisplayTool.dip2px(context, width/2F).toFloat(),
                        DisplayTool.dip2px(context, locationbottom).toFloat(),
                        mColorHight,
                        null,
                        Shader.TileMode.CLAMP
                    )


            }
        }
        mPaint.setShader(linearGradient)
        canvas.drawPath(path, mPaint)
        canvas.drawPath(pathCover, mPaintConver)
       /* path.addRoundRect(
            0f,
            value,
            40f,
            100f,
            floatArrayOf(0f, 0f, 0f, 0f, 50f, 50f, 50f, 50f),
            Path.Direction.CCW
        )
        canvas.drawPath(path, mPaint)*/
    }

    private var value: Float = 10.0F
     fun setValue(value: Float) {
        this.value = value
        invalidate()
    }

    private fun measureWidth(measureSpec:Int):Int{
        var specMode = MeasureSpec.getMode(measureSpec);
        var specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为40F，这个看我们自定义View的要求
        var result = DisplayTool.dip2px(context, 40F)
        Log.e("zpl","Width=$result")
        Log.e("zpl","WidthspecMode=$specMode")
        Log.e("zpl","WidthspecSize=$specSize")
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    private fun measureHeight(measureSpec:Int):Int{
        var specMode = MeasureSpec.getMode(measureSpec);
        var specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为100F，这个看我们自定义View的要求
        var result = DisplayTool.dip2px(context, 100F)
        Log.e("zpl","Height=$result")
        Log.e("zpl","HeightspecMode=$specMode")
        Log.e("zpl","HeightspecSize=$specSize")
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

}
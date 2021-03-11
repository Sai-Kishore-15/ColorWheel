package CustomUI

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


class ColorWheel: View {

    constructor(context: Context): this(context,null){
    }
    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr)

    val paint  = Paint(Paint.ANTI_ALIAS_FLAG)

    val saturationPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val brightnessPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val DEFAULT_BRIGHTNESS = 224

    init{
        paint.apply {
            style = Paint.Style.FILL_AND_STROKE
        }
        brightnessPaint.apply {
            setColor(Color.BLACK)
            setAlpha(brightnessToAlpha(DEFAULT_BRIGHTNESS))
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        val floatArray  = floatArrayOf(0.000f, 0.166f, 0.333f, 0.499f,
            0.666f, 0.833f, 0.999f)

        val satShader: Shader = RadialGradient(
            w/2f, h/2f, w/2f,
            Color.WHITE, 0x00FFFFFF,
            Shader.TileMode.CLAMP
        )

        val sweepShader: Shader = SweepGradient(
            w/2f, w/2f, intArrayOf(
                Color.RED,
                Color.MAGENTA,
                Color.BLUE,
                Color.CYAN,
                Color.GREEN,
                Color.YELLOW,
                Color.RED
            ),floatArray)

        saturationPaint.shader = satShader
        paint.shader = sweepShader

        super.onSizeChanged(w, h, oldw, oldh)
    }
    override fun draw(canvas: Canvas?) {

        canvas?.drawCircle(width/2f,height/2f,width/2f,paint)
        canvas?.drawCircle(width/2f,height/2f,width/2f,saturationPaint)
        canvas?.drawCircle(width/2f,height/2f,width/2f,brightnessPaint)

        super.draw(canvas)
    }

    fun setBrightness(brightness: Int) {
        var localbrightness = brightness
        if(brightness > 255) localbrightness = 255
        if (localbrightness<0) localbrightness = 0

        brightnessPaint.setAlpha(brightnessToAlpha(localbrightness))
        invalidate()
    }

    private fun brightnessToAlpha(brightness: Int): Int {
        return 255 - brightness
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }


}
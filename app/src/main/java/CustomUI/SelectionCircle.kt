package CustomUI

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.twrotu.colorwheel.general.toDp

class SelectionCircle: View{
    constructor(context: Context): this(context,null){
    }
    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr)

    val innerCircleBorder = Paint(Paint.ANTI_ALIAS_FLAG)
    val innerCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var currentX = 0f
    var currenY = 0f

    init{
        innerCircleBorder.apply {
            strokeWidth = 8f
            style = Paint.Style.STROKE
            color = Color.WHITE
        }

    }
    override fun draw(canvas: Canvas?) {
        canvas?.drawCircle(currentX, currenY,width/20f,innerCirclePaint)
        canvas?.drawCircle(currentX, currenY,width/20f,innerCircleBorder)
        super.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        innerCirclePaint.apply {
            style = Paint.Style.FILL
            color = Color.LTGRAY
            setShadowLayer(w/20f, 0f, 0f, 0x80000000.toInt())
            setLayerType(View.LAYER_TYPE_SOFTWARE, this)
        }
        currenY = h/2f
        currentX = w/2f

        super.onSizeChanged(w, h, oldw, oldh)
    }
    fun updatePosition(x: Float, y: Float){
        currentX = x
        currenY = y
        invalidate()
    }
    fun updatePaint(color: Int){
        innerCirclePaint.color = color
    }
}


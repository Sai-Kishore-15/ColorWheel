package CustomUI

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.twrotu.colorwheel.R
import com.twrotu.colorwheel.general.ButtonStatus
import com.twrotu.colorwheel.general.ScreenFunctions

class ColorPickerBox: View {
    constructor(context: Context, colorNew: Int, buttonStatus: ButtonStatus, indexNew: Int): this(context,null){
        this.color = colorNew
        this.status = buttonStatus
        this.index = indexNew

        updatePaintColor(this.color)
        updateBackground()
    }
    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr)

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bgPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var index = 0

    var status: ButtonStatus = ButtonStatus.Off
    var color = Color.WHITE

    var currentX = 0f
    var currentY = 0f

    init{
        updatePaintColor(Color.WHITE)
        paint.apply {
            style = Paint.Style.FILL
            strokeWidth = 8f
            color = Color.WHITE
        }
        bgPaint.apply {
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth  = 4f
            color = ContextCompat.getColor(context,R.color.element_background)
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawRect(0f,0f,0f + width,0f+ height,bgPaint)
        canvas?.drawCircle(width/2f,height/2f,width/6f,paint)
        super.draw(canvas)
    }

    fun updatePaintColor(colorNew: Int){
        color = colorNew
        paint.color = colorNew
        invalidate()
    }

    fun updateBackground() {
        if (this.status == ButtonStatus.Off) {
            bgPaint.color = ContextCompat.getColor(context, R.color.element_background)
        } else {
            bgPaint.color = ContextCompat.getColor(context, R.color.selected_background)
        }
        invalidate()
    }

    fun changeStatus(statusNew: ButtonStatus){
        status = statusNew
        updateBackground()
        updatePaintColor(color)
    }
    fun updatePosition(x: Float, y: Float){
        currentX = x
        currentY = y
    }
}
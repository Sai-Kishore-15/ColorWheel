package CustomUI

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.Slider
import com.twrotu.colorwheel.R


class CustomSlider: LinearLayout  {
    constructor(context: Context, name: String, min: Int = 0 , max: Int = 100): this(context,null){
        this.controlName = name
        addTextview()
        addSlider()
    }
    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr)

     var controlName = "TestName"

    init{
        // Properties
        orientation = VERTICAL
        setBackgroundColor(ContextCompat.getColor(context,R.color.element_background))
    }

    fun addTextview(){
        val textview = TextView(context)
        val textPadding = resources.getDimensionPixelSize(R.dimen.textPadding)

        textview.apply{
            setText(this@CustomSlider.controlName)
            setTextColor(Color.WHITE)
            setPadding(textPadding,textPadding,textPadding,textPadding)
            textSize = context.resources.getDimension(R.dimen.secondaryTextSize)
        }
        addView(textview)
    }

    fun addSlider(){
        val slider = Slider(context)
        slider.value = 1f
        addView(slider)
    }



}
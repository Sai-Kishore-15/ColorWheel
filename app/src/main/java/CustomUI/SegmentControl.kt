package CustomUI

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.twrotu.colorwheel.R

class SegmentControl: LinearLayout {
    constructor(context: Context, name: String, min: Int = 0 , max: Int = 100): this(context,null){
    }
    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr)

    private var subViews = ArrayList<ColorPickerBox>()

    init{
        orientation = HORIZONTAL
    }


    fun addSubViews(subViewsList: ArrayList<ColorPickerBox>){
        this.subViews.clear()
        this.subViews = subViewsList
        for (view in subViews){
            addView(view)
        }
    }


}
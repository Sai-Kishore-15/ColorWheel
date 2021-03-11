import CustomUI.ColorPickerBox
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import com.twrotu.colorwheel.Data.BoxButtonModel

//
class ViewProvider {
    companion object{
        fun getSubViews(
            context: Context,
            width: Int,
            segmentList: List<BoxButtonModel>
        ): ArrayList<ColorPickerBox> {
            val subViews = ArrayList<ColorPickerBox>()

                for (i in segmentList) {
                    Log.d("Test","${width}")

                    val segment = ColorPickerBox(context, i.color, i.status, i.index )
                    val layoutParamsNew =
                        LinearLayout.LayoutParams(width,width)
                    segment.layoutParams = layoutParamsNew
                    subViews.add(segment)
                }
            return subViews
        }
    }
}
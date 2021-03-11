package com.twrotu.colorwheel

import CustomUI.*
import ViewProvider
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.twrotu.colorwheel.Data.BoxSupplier
import com.twrotu.colorwheel.general.ButtonStatus
import com.twrotu.colorwheel.general.ScreenFunctions
import com.twrotu.colorwheel.general.toDp
import kotlin.math.pow
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    // UI From XML
    lateinit var ll_sliders: LinearLayout
    lateinit var colorwheel: ColorWheel
    lateinit var tv_hex: TextView
    lateinit var brightnessSlider: Slider
    lateinit var segmentedControl: SegmentControl
    lateinit var selectionCircle: SelectionCircle


    // ControlVariables


    // Other classes
    val screenFunctions = ScreenFunctions()
    var segmentBoxButtons: ArrayList<ColorPickerBox>? = null

    // Current Button
    var currentIndex = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        setupStatusBar()

        addSliders()

        addSegmentedButtons()

        // Listeners
        addsegmentListeners()

        brightnessSlider.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            //Use the value and set the brightness level
            colorwheel.setBrightness((value*255).roundToInt())
        })

        colorwheel.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event?.action){
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        // Null Safety
                        if (event.x < 0 || event.x > v?.width!! || event.y < 0 || event.y > v.height){
                            return true
                        }

                        // x^2 + y^2 <= r^2 ( So that the selectionCircle does not go out of bounds )
                        if (( (event.x - colorwheel.width/2).pow(2) + (event.y - colorwheel.width/2).pow(2) )  >
                                screenFunctions.getColorWheelWidthSquare(colorwheel.width) ){
                            return true
                        }

                        val bitmap: Bitmap? = screenFunctions.getBitmapFromView(colorwheel, Color.WHITE)

                        val pixel = bitmap?.getPixel(event.x.toInt(), event.y.toInt())

                        val r  = Color.red(pixel!!)
                        val g = Color.green(pixel)
                        val b = Color.blue(pixel)

                        val text2 = Integer.toHexString(pixel)
                        tv_hex.text = text2

                        // Change the color slider here
                        segmentBoxButtons?.get(currentIndex)?.updatePaintColor(Color.rgb(r,g,b))
                        segmentBoxButtons?.get(currentIndex)?.updatePosition(event.x,event.y)

                        selectionCircle.updatePosition(event.x,event.y)
                        selectionCircle.updatePaint(Color.rgb(r,g,b))

                    }
                    MotionEvent.ACTION_UP-> {
                        v?.performClick()
                    }
                }
                return true
            }
        })

    }

    fun setupStatusBar(){
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }

    fun initViews(){
        ll_sliders = findViewById(R.id.ll_sliders)
        colorwheel = findViewById(R.id.colorwheel_main)
        tv_hex = findViewById(R.id.tv_hsvColor)
        segmentedControl = findViewById(R.id.segmentcontrol_main)
        selectionCircle = findViewById(R.id.selectionCircle_main)
    }

    fun addSliders(){
        val brightnessBox = CustomSlider(this,resources.getString(R.string.brightness),0,100)
        brightnessSlider = brightnessBox.getChildAt(1) as Slider
        ll_sliders.addView(brightnessBox)
    }

    fun addSegmentedButtons(){
        val data = BoxSupplier.getsegmentedButtons(this)
        segmentBoxButtons = ViewProvider.getSubViews(this,
            screenFunctions.getDisplayWidth(this).roundToInt() / (data.size-1),
            data )
        segmentedControl.addSubViews(segmentBoxButtons!!)

    }

    fun switchOffBoxes(){
        segmentBoxButtons?.let{
            for(i in segmentBoxButtons!!){
                i.changeStatus(ButtonStatus.Off)
            }
        }
    }

    fun addsegmentListeners(){
        segmentBoxButtons?.let{
            for( i in segmentBoxButtons!!){
                i.setOnClickListener{
                    switchOffBoxes()
                    i.changeStatus(ButtonStatus.On)
                    updateSelectionCircle(i.index)
                    currentIndex = i.index
                }
            }
        }
    }
    fun updateSelectionCircle(i: Int){
        var x = segmentBoxButtons!![i].currentX
        var y = segmentBoxButtons!![i].currentY
        if (x == 0f && y == 0f ){
            x = colorwheel.width/2f
            y = colorwheel.width/2f
        }
        selectionCircle.updatePosition(x , y)
        selectionCircle.updatePaint(segmentBoxButtons!![i].color)
    }
}

package com.twrotu.colorwheel.general

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import android.util.Log
import android.view.View

class ScreenFunctions {
    private var colorWheelWidthSquare: Int = -1
     fun getBitmapFromView(view: View, defaultColor: Int): Bitmap? {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

         val canvas = Canvas(bitmap)

        canvas.drawColor(defaultColor)
        view.draw(canvas)
        return bitmap
    }

    fun getDisplayWidth(context: Context): Float{
        val dispMetrics = context.resources.displayMetrics
        val dpWidth = dispMetrics.widthPixels /  dispMetrics.density
        return dpWidth
    }

    fun getColorWheelWidthSquare(width: Int): Int{
        if (colorWheelWidthSquare == -1){
            colorWheelWidthSquare = (width/2) * (width/2)
        }
        return colorWheelWidthSquare
    }

}
fun Int.toDp(): Int = (this/Resources.getSystem().displayMetrics.density).toInt()

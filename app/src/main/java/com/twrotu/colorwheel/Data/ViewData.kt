package com.twrotu.colorwheel.Data

import android.content.Context
import android.widget.Button
import androidx.core.content.ContextCompat
import com.twrotu.colorwheel.R
import com.twrotu.colorwheel.general.ButtonStatus


/// UNUSED

 class BoxButtonModel(var name : String, var  color: Int, var index: Int, var status: ButtonStatus) {
}

object BoxSupplier  {
    val boxButtons = listOf("Box0","Box1","Box2")
    fun getsegmentedButtons(context: Context): List<BoxButtonModel>{
        val boxButtons2 = listOf<BoxButtonModel>(
            BoxButtonModel("Box0", ContextCompat.getColor(context,R.color.teal), 0, ButtonStatus.On),
            BoxButtonModel("Box1", ContextCompat.getColor(context,R.color.Green), 1, ButtonStatus.Off),
            BoxButtonModel("Box2", ContextCompat.getColor(context,R.color.Orange), 2, ButtonStatus.Off)
        )
        return boxButtons2
    }

}



package com.sungbin.kakaobot.ui.view

import android.content.*
import android.util.*
import androidx.drawerlayout.widget.DrawerLayout


/**
 * Created by SungBin on 2020-05-11.
 */

class DrawerLayout : DrawerLayout {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    )

    override fun onMeasure(widthMeasureSpecInt: Int, heightMeasureSpecInt: Int) {
        var widthMeasureSpec = widthMeasureSpecInt
        var heightMeasureSpec = heightMeasureSpecInt
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(
            MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY
        )
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
            MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
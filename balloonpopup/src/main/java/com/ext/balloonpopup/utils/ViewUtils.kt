package com.ext.balloonpopup.utils

import android.view.View

fun View.getLocation(): IntArray {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return location
}

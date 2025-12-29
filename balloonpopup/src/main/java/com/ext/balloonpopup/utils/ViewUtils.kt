package com.ext.balloonpopup.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View

fun View.getLocation(): IntArray {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return location
}

fun Context.screenSize(): Pair<Int, Int> {
    val metrics = DisplayMetrics()
    (this as Activity).windowManager.defaultDisplay.getMetrics(metrics)
    return Pair(metrics.widthPixels, metrics.heightPixels)
}

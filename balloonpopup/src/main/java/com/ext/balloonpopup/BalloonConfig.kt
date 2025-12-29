package com.ext.balloonpopup

data class BalloonConfig(
    val text: String = "",
    val backgroundColor: Int = 0xFF333333.toInt(),
    val textColor: Int = 0xFFFFFFFF.toInt(),
    val arrowPosition: ArrowPosition = ArrowPosition.BOTTOM,
    val xOffset: Int = 0,
    val yOffset: Int = 0,
    val autoFlip: Boolean = true
)


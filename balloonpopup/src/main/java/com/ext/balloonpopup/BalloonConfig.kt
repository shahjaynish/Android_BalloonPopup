package com.ext.balloonpopup

data class BalloonConfig(
    val text: String = "",
    val backgroundColor: Int = 0xFF333333.toInt(),
    val textColor: Int = 0xFFFFFFFF.toInt(),
    val arrowPosition: ArrowPosition = ArrowPosition.BOTTOM
)
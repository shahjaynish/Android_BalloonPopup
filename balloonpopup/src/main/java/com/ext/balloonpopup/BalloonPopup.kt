package com.ext.balloonpopup

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import com.ext.balloonpopup.utils.getLocation
import com.ext.balloonpopup.utils.screenSize


class BalloonPopup(private val context: Context) {

    private var config = BalloonConfig()
    private var popupWindow: PopupWindow? = null

    fun setText(text: String) = apply {
        config = config.copy(text = text)
    }

    fun setArrowPosition(position: ArrowPosition) = apply {
        config = config.copy(arrowPosition = position)
    }

    fun show(anchor: View) {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_balloon, null)

        val tvText = view.findViewById<TextView>(R.id.tvText)
        val arrow = view.findViewById<ImageView>(R.id.ivArrow)



        tvText.text = config.text
        tvText.setTextColor(config.textColor)


        val contentLayout = view.findViewById<View>(R.id.balloonContent)
        contentLayout.background.mutate().setTint(config.backgroundColor)

        // Arrow drawable
        when (config.arrowPosition) {
            ArrowPosition.TOP -> arrow.setImageResource(R.drawable.triangle_up)
            ArrowPosition.BOTTOM -> arrow.setImageResource(R.drawable.triangle)
            ArrowPosition.LEFT -> arrow.setImageResource(R.drawable.triangle_left)
            ArrowPosition.RIGHT -> arrow.setImageResource(R.drawable.triangle_right)
        }
        arrow.visibility = View.VISIBLE
        arrow.drawable.mutate().setTint(config.backgroundColor)

        // Measure popup view
        view.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )

        val popupWidth = view.measuredWidth
        val popupHeight = view.measuredHeight

        val location = anchor.getLocation()
        val anchorX = location[0]
        val anchorY = location[1]

        popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        ).apply {
            isOutsideTouchable = true
            isFocusable = true
            setBackgroundDrawable(android.graphics.drawable.ColorDrawable(0x00000000))
        }


        val (screenW, screenH) = context.screenSize()
        var pos = config.arrowPosition

        // AUTO FLIP LOGIC
        if (config.autoFlip) {
            if (pos == ArrowPosition.TOP && anchorY - popupHeight < 0) {
                pos = ArrowPosition.BOTTOM
            } else if (pos == ArrowPosition.BOTTOM &&
                anchorY + anchor.height + popupHeight > screenH
            ) {
                pos = ArrowPosition.TOP
            }
        }

        // FINAL POSITIONING
        when (pos) {

            ArrowPosition.BOTTOM -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX + anchor.width / 2 - popupWidth / 2 + config.xOffset,
                    anchorY + anchor.height + config.yOffset
                )
            }

            ArrowPosition.TOP -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX + anchor.width / 2 - popupWidth / 2 + config.xOffset,
                    anchorY - popupHeight - config.yOffset
                )
            }

            ArrowPosition.LEFT -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX - popupWidth - config.xOffset,
                    anchorY + anchor.height / 2 - popupHeight / 2 + config.yOffset
                )
            }

            ArrowPosition.RIGHT -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX + anchor.width + config.xOffset,
                    anchorY + anchor.height / 2 - popupHeight / 2 + config.yOffset
                )
            }
        }
        view.setOnClickListener {
            dismiss()
        }


    }

    fun dismiss() {
        popupWindow?.dismiss()
    }
    fun setOffset(x: Int, y: Int) = apply {
        config = config.copy(xOffset = x, yOffset = y)
    }

    fun enableAutoFlip(enable: Boolean) = apply {
        config = config.copy(autoFlip = enable)
    }
    fun setBackgroundColor(color: Int) = apply {
        config = config.copy(backgroundColor = color)
    }
    fun setTextColor(color: Int) = apply {
        config = config.copy(textColor = color)
    }


}

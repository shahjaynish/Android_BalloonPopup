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

        // Arrow drawable
        when (config.arrowPosition) {
            ArrowPosition.TOP -> arrow.setImageResource(R.drawable.triangle_up)
            ArrowPosition.BOTTOM -> arrow.setImageResource(R.drawable.triangle)
            ArrowPosition.LEFT -> arrow.setImageResource(R.drawable.triangle_left)
            ArrowPosition.RIGHT -> arrow.setImageResource(R.drawable.triangle_right)
        }
        arrow.visibility = View.VISIBLE

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
        )

        when (config.arrowPosition) {
            ArrowPosition.BOTTOM -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX + anchor.width / 2 - popupWidth / 2,
                    anchorY + anchor.height
                )
            }

            ArrowPosition.TOP -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX + anchor.width / 2 - popupWidth / 2,
                    anchorY - popupHeight
                )
            }

            ArrowPosition.LEFT -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX - popupWidth,
                    anchorY + anchor.height / 2 - popupHeight / 2
                )
            }

            ArrowPosition.RIGHT -> {
                popupWindow?.showAtLocation(
                    anchor,
                    Gravity.NO_GRAVITY,
                    anchorX + anchor.width,
                    anchorY + anchor.height / 2 - popupHeight / 2
                )
            }
        }
    }

    fun dismiss() {
        popupWindow?.dismiss()
    }
}

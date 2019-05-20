package com.hafezie.barname.utility

import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.Window
import android.view.WindowManager

object DialogMaker {
    private fun createActualDialog(context: Activity, windowFeature: Int, style: Int?, contentView: Int, gravity: Int,
                                   x: Int, y: Int, dimBehind: Boolean, width: Int, height: Int): Dialog {
        val displayWidth = MyDisplayManager.displayInPixels(context)[0]
        val dialog: Dialog = if (style == null) {
            Dialog(context)
        } else {
            Dialog(context, style)
        }

        dialog.requestWindowFeature(windowFeature)
        dialog.setContentView(contentView)

        val params = WindowManager.LayoutParams()
        params.copyFrom(dialog.window!!.attributes)
        params.gravity = gravity
        params.x = x - displayWidth / 2 + width / 2
        params.y = y
        if (dimBehind) {
            params.flags = params.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        }
        params.width = width
        params.height = height

        dialog.window!!.attributes = params
        return dialog
    }

    private fun createDialog(context: Activity, windowFeature: Int, style: Int?, contentView: Int, gravity: Int,
                             xRatio: Double, yRatio: Double, dimBehind: Boolean, widthRatio: Double, heightRatio: Double): Dialog {
        val displayWidth = MyDisplayManager.displayInPixels(context)[0]
        val displayHeight = MyDisplayManager.displayInPixels(context)[1]
        return createActualDialog(context, windowFeature, style, contentView, gravity, (displayWidth * xRatio).toInt(),
            (displayHeight * yRatio).toInt(), dimBehind, (displayWidth * widthRatio).toInt(), (displayHeight * heightRatio).toInt()
        )
    }

    fun createStandardDialog(context: Activity, contentView: Int): Dialog {
        return createDialog(
            context,
            Window.FEATURE_NO_TITLE,
            null,
            contentView,
            Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL,
            0.08,
            0.0,
            false,
            .86,
            .3
        )
    }

    fun createLongerDialog(context: Activity, contentView: Int): Dialog {
        return createDialog(
            context,
            Window.FEATURE_NO_TITLE,
            null,
            contentView,
            Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL,
            0.08,
            0.0,
            false,
            .86,
            .5
        )
    }

    fun createLonger2Dialog(context: Activity, contentView: Int): Dialog {
        return createDialog(
            context,
            Window.FEATURE_NO_TITLE,
            null,
            contentView,
            Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL,
            0.08,
            0.0,
            false,
            .86,
            .6
        )
    }

    fun createLongestDialog(context: Activity, contentView: Int): Dialog {
        return createDialog(
            context,
            Window.FEATURE_NO_TITLE,
            null,
            contentView,
            Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL,
            0.08,
            0.0,
            false,
            .86,
            .8
        )
    }
}
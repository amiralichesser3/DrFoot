package com.safari.drfoot.utility

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point

object MyDisplayManager {
    private var mDisplay: IntArray = IntArray(2)
    private var density: Float = 0f

    fun displayInPixels(activity: Activity): IntArray {
        if (mDisplay[0] != 0) return mDisplay
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        mDisplay[0] = size.x
        mDisplay[1] = size.y
        return mDisplay
    }

    fun displayWidth(activity: Activity): Int {
        return displayInPixels(activity)[0]
    }

    fun displayHeight(activity: Activity): Int {
        return displayInPixels(activity)[1]
    }

    fun isPortrait(ctx: Activity): Boolean {
        return ctx.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    fun getDensity(ctx: Context): Float {
        if (density == 0f) {
            val metrics = ctx.resources.displayMetrics
            density = metrics.density
        }

        return density
    }
}
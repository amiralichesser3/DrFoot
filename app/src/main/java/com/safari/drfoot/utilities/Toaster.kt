package com.safari.drfoot.utility

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import com.safari.drfoot.BuildConfig

object Toaster {
    fun toast(toast: String, context: Context, isRelease: Boolean) {
        toast(toast, context, android.widget.Toast.LENGTH_SHORT, isRelease)
    }

    fun toastLong(toast: String, context: Context, isRelease: Boolean) {
        toast(toast, context, android.widget.Toast.LENGTH_LONG, isRelease)
    }

    fun toast(toast: String, context: Context?, length: Int, isRelease: Boolean) {
        if (context == null || TextUtils.isEmpty(toast)) {
            return
        }
        if (BuildConfig.DEBUG || isRelease) {
            if (context != null) {
                val mToast = android.widget.Toast.makeText(context, toast, length)
                try {
                    val toastLayout = mToast.view as LinearLayout
                    toastLayout.setBackgroundColor(Color.parseColor("#4D4D4D"))
                    val toastTV = toastLayout.getChildAt(0) as TextView
                    toastTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
                    toastTV.setPadding(12, 0 ,12, 0)
                } catch (ex: Exception) {
                    //ignored
                }

                mToast.show()
            }
        }
    }
}
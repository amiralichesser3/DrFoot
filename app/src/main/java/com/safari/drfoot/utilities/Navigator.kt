package com.hafezie.barname.utility

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.safari.drfoot.R

class Navigator private constructor(private val bundle: Bundle?) {

    companion object {
        fun withBundle(bundle: Bundle): Navigator {
            return Navigator(bundle)
        }

        fun withouthBundle(): Navigator {
            return Navigator(null)
        }
    }

    fun changeActivity(oldActivity: Activity, newActivity: Class<*>, shouldFinish: Boolean) {
        val intent = Intent(oldActivity, newActivity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        oldActivity.startActivity(intent)
        if (shouldFinish) {
            oldActivity.finish()
        }
    }

    fun changeActivityFade(oldActivity: Activity, newActivity: Class<*>, shouldFinish: Boolean) {
        val intent = Intent(oldActivity, newActivity)
        if (bundle != null) {
            intent.putExtras(bundle!!)
        }
        oldActivity.startActivity(intent)
        oldActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        if (shouldFinish) {
            oldActivity.finish()
        }
    }
}
package com.safari.drfoot.utility

import android.content.Context

object SharedPreferencesHelper {
    val PARENT: String = "parent"

    fun getString(context: Context?, parent: String, `var`: String, default_value: String?): String? {
        if (context == null) {
            return null
        }
        val shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE)
        return shared.getString(`var`, default_value)
    }

    fun setString(context: Context?, parent: String, key: String, value: String?) {
        if (context == null) {
            return
        }
        val shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE)
        shared.edit().putString(key, value).apply()
    }

    fun clearParent(context: Context?, parent: String) {
        if (context == null) {
            return
        }
        val shared = context.getSharedPreferences(parent, Context.MODE_PRIVATE)
        shared.edit().clear().apply()
    }
}
package com.hafezie.barname.utility

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat

object PermissionHelper {
    const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 111
    const val MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 112
    const val MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 113
    const val MY_PERMISSIONS_REQUEST_CAMERA = 114

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkForReadPermission(activity: Activity): Boolean {
        return checkForPermission(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE)
    }

    fun checkForReadPhoneStatePermission(activity: Activity): Boolean {
        return checkForPermission(activity, arrayOf(Manifest.permission.READ_PHONE_STATE),
            MY_PERMISSIONS_REQUEST_READ_PHONE_STATE)
    }

    fun checkForContactsPermission(activity: Activity): Boolean {
        return checkForPermission(activity, arrayOf(Manifest.permission.READ_CONTACTS),
            MY_PERMISSIONS_REQUEST_READ_CONTACTS)
    }

    fun checkForCameraPermission(activity: FragmentActivity): Boolean {
        return checkForPermission(activity, arrayOf(Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_CAMERA)
    }

    private fun checkForPermission(activity: Activity, permissions: Array<String>, code: Int): Boolean {
        var flag = false
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                flag = true
                break
            }
        }

        if (flag) {
            ActivityCompat.requestPermissions(activity, permissions, code)
            return false
        }

        return true
    }
}
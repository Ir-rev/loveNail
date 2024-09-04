package ru.pervov.lovenail.permissions_getter

import android.app.Activity
import android.content.pm.PackageManager

private const val PERMISSION_INSTALL_REQUEST = 2

class PermissionsGetter {

    fun getPermissions(activity: Activity, permissionsList: List<String>): Boolean {
        val needGetPermission = mutableListOf<String>()
        for (permission in permissionsList) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                needGetPermission.add(permission)
            }
        }
        if (needGetPermission.isEmpty()) return true
        activity.requestPermissions(needGetPermission.toTypedArray(), PERMISSION_INSTALL_REQUEST)
        return false
    }

}
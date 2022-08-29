package com.piroworkz.architectcoders.app.data

import com.piroworkz.architectcoders.data.source.PermissionChecker


class FakeAndroidPermissionChecker(private val granted: Boolean) : PermissionChecker {

    override fun check(permission: PermissionChecker.Permission): Boolean {
        return granted
    }
}
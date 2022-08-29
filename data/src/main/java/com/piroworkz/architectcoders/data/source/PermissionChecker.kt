package com.piroworkz.architectcoders.data.source

interface PermissionChecker {
    enum class Permission { COARSE_LOCATION }
    fun check(permission: Permission): Boolean
}
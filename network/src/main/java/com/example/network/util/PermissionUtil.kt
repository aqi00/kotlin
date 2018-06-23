package com.example.network.util

import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.os.Build
import android.app.Activity
import android.content.Context


/**
 * Created by ouyangshen on 2018/6/20.
 */
object PermissionUtil {

    // 检查某个权限。返回true表示已启用该权限，返回false表示未启用该权限
    fun checkPermission(act: Activity, permission: String, requestCode: Int): Boolean {
        var result = true
        // 只对Android6.0及以上系统进行校验
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查当前App是否开启了名称为permission的权限
            val check = ContextCompat.checkSelfPermission(act, permission)
            if (check != PackageManager.PERMISSION_GRANTED) {
                // 未开启该权限，则请求系统弹窗，好让用户选择是否立即开启权限
                ActivityCompat.requestPermissions(act, arrayOf(permission), requestCode)
                result = false
            }
        }
        return result
    }

    // 检查多个权限。返回true表示已完全启用权限，返回false表示未完全启用权限
    fun checkMultiPermission(act: Activity, permissions: Array<String>, requestCode: Int): Boolean {
        var result = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var check = PackageManager.PERMISSION_GRANTED
            // 通过权限数组检查是否都开启了这些权限
            for (permission in permissions) {
                check = ContextCompat.checkSelfPermission(act, permission)
                if (check != PackageManager.PERMISSION_GRANTED) {
                    break
                }
            }
            if (check != PackageManager.PERMISSION_GRANTED) {
                // 未开启该权限，则请求系统弹窗，好让用户选择是否立即开启权限
                ActivityCompat.requestPermissions(act, permissions, requestCode)
                result = false
            }
        }
        return result
    }

    fun goActivity(ctx: Context, cls: Class<*>) {
        val intent = Intent(ctx, cls)
        ctx.startActivity(intent)
    }

}
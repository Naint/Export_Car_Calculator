package com.example.japancars.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.japancars.MainActivity

const val USE_INTERNET = Manifest.permission.INTERNET
const val PERMISSION_REQUEST = 200
fun checkPermission(permission: String, context: Context, activity: MainActivity): Boolean{
    return if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(activity, arrayOf(permission), PERMISSION_REQUEST)
        false
    } else true
}


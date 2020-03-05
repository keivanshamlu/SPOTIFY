package com.shamlu.common.extentions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.Settings
import android.util.TypedValue
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

fun Context.startAppSettings() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    intent.addCategory(Intent.CATEGORY_DEFAULT)
    intent.data = Uri.parse("package:$packageName")
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(intent)
}

/**
 *  Starts wifi setting
 */
fun Context.startNetworkSettings() {
    val intent = Intent()
    intent.action = Settings.ACTION_WIFI_SETTINGS
    intent.addCategory(Intent.CATEGORY_DEFAULT)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(intent)
}

/**
 *  Starts device setting
 */
fun Context.startDeviceSettings() {
    val intent = Intent()
    intent.action = Settings.ACTION_SETTINGS
    intent.addCategory(Intent.CATEGORY_DEFAULT)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(intent)
}


fun Context.px(dp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this.resources.displayMetrics)

fun Context.colorOf(colorResId: Int): Int = ContextCompat.getColor(this, colorResId)

fun Context.saveImageFile(bitmap: Bitmap, compress: Int, fileName: String) : File? {
    try {

        val path = File(filesDir,  "docs")
        if (!path.exists()) {
            path.mkdirs()
        }
        val outFile = File(path, "$fileName.png")
        val outputStream = FileOutputStream(outFile)

        FileOutputStream(outFile).apply {
            bitmap.compress(Bitmap.CompressFormat.JPEG, compress, this)
        }

        outputStream.close()

        return outFile
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}
package com.bnyro.wallpaper.ui.models

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bnyro.wallpaper.App
import com.bnyro.wallpaper.db.obj.Wallpaper
import com.bnyro.wallpaper.enums.MultiState
import com.bnyro.wallpaper.enums.WallpaperTarget
import com.bnyro.wallpaper.util.ImageHelper
import com.bnyro.wallpaper.util.WallpaperHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileOutputStream

class WallpaperHelperModel(private val application: Application) : ViewModel() {
    var setWallpaperState by mutableStateOf(MultiState.IDLE)
    var saveWallpaperState by mutableStateOf(MultiState.IDLE)

    fun setWallpaper(wallpaper: Wallpaper, targetIndex: Int) {
        viewModelScope.launch {
            setWallpaperState = MultiState.RUNNING
            val bitmap = withContext(Dispatchers.IO) {
                ImageHelper.urlToBitmap(
                    wallpaper.imgSrc,
                    application.applicationContext
                )
            }
            if (bitmap == null) {
                setWallpaperState = MultiState.ERROR
                return@launch
            }
            WallpaperHelper.setWallpaper(
                application.applicationContext,
                convertToNonHardwareBitmap(bitmap),
                WallpaperTarget.values()[targetIndex]
            )
            setWallpaperState = MultiState.SUCCESS
        }
    }

    fun setWallpaperWithFilter(wallpaper: Wallpaper, targetIndex: Int) {
        viewModelScope.launch {
            setWallpaperState = MultiState.RUNNING
            val bitmap = withContext(Dispatchers.IO) {
                ImageHelper.urlToBitmap(
                    wallpaper.imgSrc,
                    application.applicationContext
                )
            }
            if (bitmap == null) {
                setWallpaperState = MultiState.ERROR
                return@launch
            }
            WallpaperHelper.setWallpaperWithFilters(
                application.applicationContext,
                convertToNonHardwareBitmap(bitmap),
                WallpaperTarget.values()[targetIndex]
            )
            setWallpaperState = MultiState.SUCCESS
        }
    }

    fun saveWallpaper(wallpaper: Wallpaper, uri: Uri?) {
        if (uri == null) return
        viewModelScope.launch {
            saveWallpaperState = MultiState.RUNNING
            val bitmap = withContext(Dispatchers.IO) {
                ImageHelper.urlToBitmap(
                    wallpaper.imgSrc,
                    application.applicationContext
                )
            }
            if (bitmap == null) {
                saveWallpaperState = MultiState.ERROR
                return@launch
            }
            saveWallpaperState = try {
                withContext(Dispatchers.IO) {
                    application.contentResolver.openFileDescriptor(uri, "w")?.use {
                        FileOutputStream(it.fileDescriptor).use { fos ->
                            bitmap.compress(Bitmap.CompressFormat.PNG, 25, fos)
                        }
                    }
                }
                MultiState.SUCCESS
            } catch (e: Exception) {
                MultiState.ERROR
            }
        }

    }

    private fun convertToNonHardwareBitmap(bitmap: Bitmap): Bitmap {
        return bitmap.copy(Bitmap.Config.ARGB_8888, true)
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as App
                WallpaperHelperModel(application)
            }
        }
    }
}
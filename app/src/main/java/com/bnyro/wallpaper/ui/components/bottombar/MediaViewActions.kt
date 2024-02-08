package com.bnyro.wallpaper.ui.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.bnyro.wallpaper.db.obj.Wallpaper

@Composable
fun WallpaperViewActions(
    wallpaper: Wallpaper,
    color: Color = Color.White,
    onClickEdit: () -> Unit
) {
    val context = LocalContext.current
    IconWithText(
        imageVector = Icons.Rounded.Share,
        title = "Share",
        color = color
    ) {

    }
    IconWithText(
        imageVector = Icons.Rounded.Edit,
        title = "Edit",
        color = color
    ) {
        onClickEdit.invoke()
    }
    IconWithText(
        imageVector = Icons.Rounded.Info,
        title = "Info",
        color = color
    ) {
        //onClickMoreOptions.invoke()
    }
}

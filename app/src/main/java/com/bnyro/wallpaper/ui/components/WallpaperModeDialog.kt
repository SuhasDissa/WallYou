package com.bnyro.wallpaper.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bnyro.wallpaper.R
import com.bnyro.wallpaper.db.DatabaseHolder
import com.bnyro.wallpaper.db.obj.Wallpaper
import com.bnyro.wallpaper.ext.awaitQuery
import com.bnyro.wallpaper.ui.components.dialogs.ListDialog
import com.bnyro.wallpaper.ui.models.WallpaperHelperModel
import com.bnyro.wallpaper.util.Preferences

@Composable
fun WallpaperModeDialog(
    wallpaper: Wallpaper,
    wallpaperHelperModel: WallpaperHelperModel,
    onDismissRequest: () -> Unit,
    onLike: () -> Unit = {},
    applyFilter: Boolean = false
) {
    ListDialog(
        title = stringResource(R.string.set_wallpaper),
        items = listOf(
            stringResource(R.string.both),
            stringResource(R.string.home),
            stringResource(R.string.lockscreen)
        ),
        onDismissRequest = onDismissRequest,
        onClick = { index ->
            if (Preferences.getBoolean(Preferences.autoAddToFavoritesKey, false)) {
                onLike.invoke()
                awaitQuery {
                    DatabaseHolder.Database.favoritesDao().insertAll(wallpaper)
                }
            }
            if (applyFilter) {
                wallpaperHelperModel.setWallpaperWithFilter(wallpaper = wallpaper, index)
            } else {
                wallpaperHelperModel.setWallpaper(wallpaper = wallpaper, index)
            }
            onDismissRequest.invoke()
        }
    )
}
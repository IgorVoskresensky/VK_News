package ru.ivos.vk_news.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.ivos.vk_news.R

sealed class NavigationItem(
    val resId: Int,
    val icon: ImageVector
) {

    object Home : NavigationItem(
        resId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )
    object Favorite : NavigationItem(
        resId = R.string.navigation_item_favorite,
        icon = Icons.Outlined.Favorite
    )
    object Profile : NavigationItem(
        resId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}

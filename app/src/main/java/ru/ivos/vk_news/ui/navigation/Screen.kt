package ru.ivos.vk_news.ui.navigation

sealed class Screen(
    val route: String
) {

    object News : Screen (ROUTE_NEWS)
    object Favorite : Screen (ROUTE_FAVORITE)
    object Profile : Screen (ROUTE_PROFILE)

    companion object {

        const val ROUTE_NEWS = "news"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
    }
}

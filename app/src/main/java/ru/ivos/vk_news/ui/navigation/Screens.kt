package ru.ivos.vk_news.ui.navigation

import android.net.Uri
import com.google.gson.Gson
import ru.ivos.vk_news.domain.FeedPost

sealed class Screens(
    val route: String
) {

    object News : Screens (ROUTE_NEWS)
    object Favorite : Screens (ROUTE_FAVORITE)
    object Profile : Screens (ROUTE_PROFILE)
    object Home : Screens (ROUTE_HOME)

    object Comments : Screens (ROUTE_COMMENTS) {

        const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "${ROUTE_FOR_ARGS}/${feedPostJson.encode()}"
        }
    }

    companion object {
        const val KEY_FEED_POST = "feed_post"

        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS = "news"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
    }
}

fun String.encode(): String {
   return Uri.encode(this)
}

package ru.ivos.vk_news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import ru.ivos.vk_news.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(
        startDestination = Screens.News.route,
        route = Screens.Home.route
    ) {
        composable(Screens.News.route) {
            newsScreenContent()
        }
        composable(
            route = Screens.Comments.route,
            arguments = listOf(
                navArgument(Screens.KEY_FEED_POST) {
                    type = NavType.StringType
                }
            )
        ) {
            val feedPostJson = it.arguments?.getString(Screens.KEY_FEED_POST) ?: ""
            val feedPost = Gson().fromJson(feedPostJson, FeedPost::class.java)
            commentsScreenContent(feedPost)
        }
    }
}
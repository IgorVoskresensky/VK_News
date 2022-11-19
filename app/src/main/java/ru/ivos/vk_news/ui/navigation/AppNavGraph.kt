package ru.ivos.vk_news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.ivos.vk_news.domain.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.route
    ) {
        homeScreenNavGraph(
            newsScreenContent = newsScreenContent,
            commentsScreenContent = commentsScreenContent
        )
        composable(Screens.Favorite.route) {
            favoriteScreenContent()
        }
        composable(Screens.Profile.route) {
            profileScreenContent()
        }
    }
}
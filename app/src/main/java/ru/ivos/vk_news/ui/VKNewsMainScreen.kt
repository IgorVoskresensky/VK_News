package ru.ivos.vk_news.ui

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.ivos.vk_news.domain.FeedPost
import ru.ivos.vk_news.ui.navigation.AppNavGraph
import ru.ivos.vk_news.ui.navigation.NavigationItem
import ru.ivos.vk_news.ui.navigation.Screens
import ru.ivos.vk_news.ui.navigation.rememberNavigationState
import ru.ivos.vk_news.ui.screens.CommentsScreen
import ru.ivos.vk_news.ui.screens.HomeScreen

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    Scaffold(

        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.resId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }

    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
                )
            },
            favoriteScreenContent = { Text(text = "Favorite", color = Color.Black) },
            profileScreenContent = { Text(text = "Profile", color = Color.Black) })
    }
}
package ru.ivos.vk_news.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.ivos.vk_news.NewsViewModel
import ru.ivos.vk_news.domain.FeedPost
import ru.ivos.vk_news.ui.PostCard

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit
) {

    val viewModel: NewsViewModel = viewModel()

    val screenState = viewModel.screenState.observeAsState(NewsScreenState.Initial)

    when (val currentState = viewModel.screenState.value) {
        is NewsScreenState.Posts -> {
            FeedPosts(
                posts = currentState.posts,
                viewModel = viewModel,
                paddingValues = paddingValues,
                onCommentClickListener = onCommentClickListener
            )
        }
        is NewsScreenState.Initial -> {

        }
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: NewsViewModel,
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = posts, key = { it.id }) { feedPost ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.deletePost(feedPost)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart)
            ) {
                PostCard(
                    post = feedPost,
                    onViewsItemClickListener = { stat ->
                        viewModel.updateCount(feedPost, stat)
                    },
                    onSharesItemClickListener = { stat ->
                        viewModel.updateCount(feedPost, stat)
                    },
                    onCommentsItemClickListener = {
                        onCommentClickListener(feedPost)
                    },
                    onLikesItemClickListener = { stat ->
                        viewModel.updateCount(feedPost, stat)
                    },
                )
            }

        }
    }
}

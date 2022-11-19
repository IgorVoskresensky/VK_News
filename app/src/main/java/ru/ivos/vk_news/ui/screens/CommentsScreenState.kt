package ru.ivos.vk_news.ui.screens

import ru.ivos.vk_news.domain.FeedPost
import ru.ivos.vk_news.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
        ) : CommentsScreenState()
}

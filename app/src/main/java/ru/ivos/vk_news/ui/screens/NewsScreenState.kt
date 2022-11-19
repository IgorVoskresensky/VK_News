package ru.ivos.vk_news.ui.screens

import ru.ivos.vk_news.domain.FeedPost

sealed class NewsScreenState {

    object Initial : NewsScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsScreenState()
}

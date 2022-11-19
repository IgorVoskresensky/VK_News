package ru.ivos.vk_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ivos.vk_news.domain.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}
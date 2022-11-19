package ru.ivos.vk_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ivos.vk_news.domain.FeedPost
import ru.ivos.vk_news.domain.PostComment
import ru.ivos.vk_news.ui.screens.CommentsScreenState

class CommentsViewModel(feedPost: FeedPost) : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    fun loadComments(feedPost: FeedPost) {

        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }

        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }
}
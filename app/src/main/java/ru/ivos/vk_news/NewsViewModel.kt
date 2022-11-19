package ru.ivos.vk_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ivos.vk_news.domain.FeedPost
import ru.ivos.vk_news.domain.StatisticItem
import ru.ivos.vk_news.ui.screens.NewsScreenState

class NewsViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val initialState = NewsScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<NewsScreenState>(initialState)
    val screenState: LiveData<NewsScreenState> = _screenState

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = _screenState.value

        if(currentState !is NewsScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if(oldItem.type == item.type){
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsScreenState.Posts(posts = newPosts)
    }

    fun deletePost(item: FeedPost) {
        val currentState = _screenState.value

        if(currentState !is NewsScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(item)
        _screenState.value = NewsScreenState.Posts(posts = oldPosts)
    }
}
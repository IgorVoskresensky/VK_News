package ru.ivos.vk_news.domain

import ru.ivos.vk_news.R

data class PostComment(
    val id: Int,
    val authorName: String = "author",
    val authorAvatarId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long comment here",
    val publicationDate: String
)

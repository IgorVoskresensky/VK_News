package ru.ivos.vk_news.domain

import ru.ivos.vk_news.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResIs: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "I have farted 40 times today!",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 0),
        StatisticItem(StatisticType.SHARES, 0),
        StatisticItem(StatisticType.COMMENTS, 0),
        StatisticItem(StatisticType.LIKES, 0)
    )
)
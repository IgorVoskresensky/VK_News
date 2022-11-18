package ru.ivos.vk_news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.ivos.vk_news.R
import ru.ivos.vk_news.domain.FeedPost
import ru.ivos.vk_news.domain.StatisticItem
import ru.ivos.vk_news.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: FeedPost,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onSharesItemClickListener: (StatisticItem) -> Unit,
    onCommentsItemClickListener: (StatisticItem) -> Unit,
    onLikesItemClickListener: (StatisticItem) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            PostHeader(post)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.contentText)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = post.contentImageResId),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                statistics = post.statistics,
                onViewsItemClickListener = onViewsItemClickListener,
                onSharesItemClickListener = onSharesItemClickListener,
                onCommentsItemClickListener = onCommentsItemClickListener,
                onLikesItemClickListener = onLikesItemClickListener
            )
        }

    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onSharesItemClickListener: (StatisticItem) -> Unit,
    onCommentsItemClickListener: (StatisticItem) -> Unit,
    onLikesItemClickListener: (StatisticItem) -> Unit
) {
    Row() {
        Row(modifier = Modifier.weight(1f)) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views_count,
                text = viewsItem.count.toString(),
                onItemClickListener = {
                    onViewsItemClickListener(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = sharesItem.count.toString(),
                onItemClickListener = {
                    onSharesItemClickListener(sharesItem)
                })
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentsItem.count.toString(),
                onItemClickListener = {
                    onCommentsItemClickListener(commentsItem)
                }
            )
            IconWithText(
                iconResId = R.drawable.ic_like,
                text = likesItem.count.toString(),
                onItemClickListener = {
                    onLikesItemClickListener(likesItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw java.lang.IllegalStateException()
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onItemClickListener()
        }) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "",
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = MaterialTheme.colors.onSecondary)
    }
}

@Composable
private fun PostHeader(post: FeedPost) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = post.avatarResIs),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = post.communityName,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.publicationDate,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert, contentDescription = "",
            tint = MaterialTheme.colors.onSecondary
        )
    }
}


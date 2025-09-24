package com.sm.keepmarket.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.FeaturedCard
import com.sm.keepmarket.util.FeaturedType

@Composable
fun FeaturedCardButton(featuredCard: FeaturedCard, onClick: (FeaturedCard) -> Unit) {

    var icon = if(featuredCard.featuredType.value == FeaturedType.MARKET.value) R.drawable.marketlisticon else R.drawable.calendaricon
    var typeLabel = if(featuredCard.featuredType.value == FeaturedType.MARKET.value) stringResource(R.string.title_market) else stringResource(R.string.title_pantry)

    Box(
        modifier = Modifier
            .clickable(enabled = true, onClick = { onClick(featuredCard) })
    ) {
        Column(
            modifier = Modifier
                .size(width = 150.dp, height = 150.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp),
                    painter = painterResource(icon),
                    tint = Color.White,
                    contentDescription = ""
                )
                Text(
                    typeLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = featuredCard.name,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = featuredCard.getFormattedLastUpdate(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 10.sp
            )
        }
    }
}
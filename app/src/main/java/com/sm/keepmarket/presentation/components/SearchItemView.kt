package com.sm.keepmarket.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.SearchItem
import com.sm.keepmarket.util.HighlightType

@Composable
fun SearchItemView(searchItem: SearchItem, onExpanded: (Boolean) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable { onExpanded(!searchItem.expanded) },
            verticalAlignment = Alignment.CenterVertically
        ) {

            val resourceTitle = when(searchItem.title){
                HighlightType.PRICE_INCREASE.value -> {
                    stringResource(R.string.title_price_increase)
                }
                HighlightType.PRICE_DECREASE.value -> {
                    stringResource(R.string.title_price_decrease)
                }
                HighlightType.NEARING_EXPIRATION.value -> {
                    stringResource(R.string.title_nearing_expiration)
                }

                else -> {"Not founded"}
            }


            Text(resourceTitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .graphicsLayer(scaleY = if (searchItem.expanded) -1f else 1f),
                painter = painterResource(R.drawable.arrowdownblackicon),
                tint = Color.Unspecified,
                contentDescription = "Arrow down"
            )
        }

        AnimatedVisibility(searchItem.expanded) {
            Column {
                searchItem.items.forEach { item ->
                    HighlightItemView(item)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }

        }
    }
}
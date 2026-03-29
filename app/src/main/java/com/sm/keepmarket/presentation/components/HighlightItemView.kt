package com.sm.keepmarket.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.presentation.theme.ButtonDefault

@Composable
fun HighlightItemView(highlight: Highlight) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(R.drawable.analyticsicon),
                tint = Color.White,
                contentDescription = ""
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        modifier = Modifier,
                        text = highlight.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp
                    )

                    Row {
                        if (!highlight.description.isNullOrBlank()) {
                            Text(
                                modifier = Modifier,
                                text = highlight.description,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 15.sp
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Icon(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(highlight.icon),
                            tint = Color.Unspecified,
                            contentDescription = ""
                        )
                    }
                }

            }
            Text(
                highlight.subTitle,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = ButtonDefault
            )
        }
    }
}
package com.sm.keepmarket.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.NotificationItem

@Composable
fun NotificationItemView(notificationItem: NotificationItem, onDeleteItem: (NotificationItem) -> Unit) {

    var showDeleteAlert by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp)) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp), contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.notificationicon),
                    tint = Color.Unspecified,
                    contentDescription = "Notification icon"
                )
            }
        }

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = notificationItem.title, style = MaterialTheme.typography.labelMedium)
            Text(
                text = notificationItem.subTitle,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(
            modifier = Modifier
                .size(10.dp)
                .weight(1f)
        )

        IconButton(onClick = {
            showDeleteAlert = true
        }) {
            Icon(
                painter = painterResource(R.drawable.deleteicon),
                tint = Color.Unspecified,
                contentDescription = "Delete notification button"
            )
        }

    }

    if(showDeleteAlert){
        AlertDialog(
            onDismissRequest = { showDeleteAlert = false },
            title = { Text(stringResource(R.string.label_confirmation)) },
            text = { Text(stringResource(R.string.message_delete_item_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteItem(notificationItem)
                    showDeleteAlert = false
                }) {
                    Text(stringResource(R.string.label_yes), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteAlert = false
                }) {
                    Text(stringResource(R.string.label_no), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            }
        )
    }
}
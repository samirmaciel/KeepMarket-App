package com.sm.keepmarket.presentation.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.components.NotificationItemView
import com.sm.keepmarket.domain.model.NotificationItem
import com.sm.keepmarket.util.UiStateView
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotificationView(paddingValues: PaddingValues) {

    val viewModel : NotificationsViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    var showDeleteAllAlert by remember { mutableStateOf(false) }
    val navController = LocalNavHostController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                IconButton(modifier = Modifier.padding(16.dp), onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.arrowlefticon),
                        tint = Color.Unspecified,
                        contentDescription = "Arrow back view"
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = stringResource(R.string.title_notifications),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 25.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    showDeleteAllAlert = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(stringResource(R.string.label_remove_all_notifications), color = MaterialTheme.colorScheme.onTertiary, style = MaterialTheme.typography.labelMedium, fontSize = 12.sp)
            }
        }

        when(uiState){
            is UiStateView.Error -> {}
            is UiStateView.Loading -> {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiStateView.Success -> {
                val itemList = (uiState as UiStateView.Success<List<NotificationItem>>).data
                LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                    items(itemList) { item ->
                        NotificationItemView(item){ notificationItem ->
                            viewModel.delete(notificationItem)
                        }
                    }
                }
            }
        }
    }

    if(showDeleteAllAlert){
        AlertDialog(
            onDismissRequest = { showDeleteAllAlert = false },
            title = { Text(stringResource(R.string.label_confirmation)) },
            text = { Text(stringResource(R.string.message_clear_notifications_confirmation)) },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteAll()
                    showDeleteAllAlert = false
                }) {
                    Text(stringResource(R.string.label_yes), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteAllAlert = false
                }) {
                    Text(stringResource(R.string.label_no), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            }
        )
    }
}

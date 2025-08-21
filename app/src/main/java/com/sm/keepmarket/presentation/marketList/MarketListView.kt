package com.sm.keepmarket.presentation.marketList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.components.MarketListItem
import com.sm.keepmarket.presentation.modal.AddNewItemModal
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.Red
import com.sm.keepmarket.util.Mock

@Composable
fun MarketListView(paddingValues: PaddingValues) {

    var showAddNewItemModal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(paddingValues)
    ) {

        IconButton(modifier = Modifier.padding(16.dp), onClick = {

        }) {
            Icon(
                painter = painterResource(R.drawable.arrowlefticon),
                tint = Color.Unspecified,
                contentDescription = "Arrow back view"
            )
        }

        Text(
            modifier = Modifier.padding(16.dp),
            text = "Market List Name",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    showAddNewItemModal = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Add new Item", style = MaterialTheme.typography.labelMedium, fontSize = 12.sp)
            }

            if (showAddNewItemModal) {
                AddNewItemModal(onDismiss = { showAddNewItemModal = false }, onFinish = {
                    showAddNewItemModal = false
                })
            }

            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .weight(1f)
            )

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    "Uncheck all items",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 12.sp
                )
            }
        }

        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
            items(Mock.getMarketItemList()) { item ->
                MarketListItem(item)
            }
        }

        Spacer(modifier = Modifier
            .size(1.dp)
            .weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Total: R$1,25",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier
                .size(10.dp)
                .weight(1f))
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = "Amount: ${Mock.getMarketItemList().size}",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
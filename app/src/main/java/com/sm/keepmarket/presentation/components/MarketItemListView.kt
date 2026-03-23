package com.sm.keepmarket.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.presentation.modal.CheckMarketItemModal
import com.sm.keepmarket.presentation.modal.EditMarketItemModal
import java.math.BigDecimal

@Composable
fun MarketItemListView(
    marketItem: MarketItem,
    onEdit: (MarketItem) -> Unit,
    onDeleted: (MarketItem) -> Unit
) {

    var showEditItemModal by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(marketItem.isChecked) }
    var expandedMoreActions by remember { mutableStateOf(false) }
    var showCheckMarketItemModal by remember { mutableStateOf(false) }

    LaunchedEffect(marketItem.isChecked){
        checked = marketItem.isChecked
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                showCheckMarketItemModal = it

                if (!it) {
                    val newMarketItem = marketItem.copy(
                        isChecked = false
                    )
                    onEdit(newMarketItem)
                }
            }
        )

        if (showCheckMarketItemModal) {
            CheckMarketItemModal(
                marketItem = marketItem,
                onDismiss = {
                    checked = false
                    showCheckMarketItemModal = false
                },
                onFinish = { productName, amount, price ->
                    val newMarketItem = marketItem.copy(
                        isChecked = checked,
                        amount = amount,
                        productName = productName,
                        price = price
                    )

                    onEdit(newMarketItem)

                    showCheckMarketItemModal = false
                })
        }

        Text(
            marketItem.name,
            style = MaterialTheme.typography.titleLarge.copy(textDecoration = if (checked) TextDecoration.LineThrough else null),
            fontSize = 15.sp
        )

        Spacer(
            modifier = Modifier
                .size(10.dp)
                .weight(1f)
        )

        if (marketItem.price > BigDecimal.ZERO) {
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = marketItem.getFormattedPrice(),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp
            )
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = "● ${marketItem.amount}",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp
            )
        }

        Box() {
            IconButton(onClick = {
                expandedMoreActions = true
            }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.moreverticon),
                    tint = Color.Black,
                    contentDescription = "More button"
                )
            }

            DropdownMenu(
                modifier = Modifier.background(color = Color.White),
                expanded = expandedMoreActions,
                onDismissRequest = { expandedMoreActions = false }
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            showEditItemModal = true
                            expandedMoreActions = false
                        }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.editicon),
                        contentDescription = "Editar"
                    )
                }

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { showDeleteDialog = true }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.deleteicon),
                        contentDescription = "Excluir"
                    )
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertMessage(stringResource(R.string.message_delete_item_confirm), onDismiss = {
            showDeleteDialog = false
            expandedMoreActions = false
        }, onConfirm = {
            onDeleted(marketItem)
            showDeleteDialog = false
            expandedMoreActions = false
        })
    }

    if (showEditItemModal) {
        EditMarketItemModal(
            marketItem,
            onDismiss = { showEditItemModal = false },
            onFinish = { productName, name, amount, price ->
                val newMarketItem = marketItem.copy(
                    name = name,
                    productName = productName,
                    amount = amount,
                    price = price
                )

                onEdit(newMarketItem)
                showEditItemModal = false
            })
    }

}
package com.sm.keepmarket.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
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
import com.sm.keepmarket.domain.model.PantryItem
import com.sm.keepmarket.presentation.modal.EditPantryItemModal

@Composable
fun PantryItemListView(
    pantryItem: PantryItem,
    onEdit: (PantryItem) -> Unit,
    onDeleted: (PantryItem) -> Unit
) {

    var showEditItemModal by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var expandedMoreActions by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .height(60.dp)
            .background(color = pantryItem.getDueColor(), shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                pantryItem.name,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp,
                color = Color.White
            )

            Text(
                pantryItem.getTimeLeft(),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Color.White
            )
        }

        Spacer(
            modifier = Modifier
                .size(10.dp)
                .weight(1f)
        )


        Text(
            modifier = Modifier.padding(end = 5.dp),
            text = "● ${pantryItem.amount}",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 15.sp,
            color = Color.White
        )


        Box() {
            IconButton(onClick = {
                expandedMoreActions = true
            }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.moreverticon),
                    tint = Color.White,
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
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(R.string.label_confirmation)) },
            text = { Text(stringResource(R.string.message_delete_item_confirm)) },
            confirmButton = {
                TextButton(onClick = {
                    onDeleted(pantryItem)
                    showDeleteDialog = false
                    expandedMoreActions = false
                }) {
                    Text(stringResource(R.string.label_yes), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    expandedMoreActions = false
                }) {
                    Text(stringResource(R.string.label_no), style = MaterialTheme.typography.labelMedium, color = Color.Black)
                }
            }
        )
    }

    if (showEditItemModal) {
        EditPantryItemModal(
            pantryItem,
            onDismiss = { showEditItemModal = false },
            onFinish = { name, amount, dueDate ->
                val newPantryItem = pantryItem.copy(
                    name = name,
                    amount = amount,
                    dueDate = dueDate
                )

                onEdit(newPantryItem)
                showEditItemModal = false
            })
    }
}
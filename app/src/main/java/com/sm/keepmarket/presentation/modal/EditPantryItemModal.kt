package com.sm.keepmarket.presentation.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sm.keepmarket.domain.model.PantryItem
import java.time.LocalDate

@Composable
fun EditPantryItemModal(pantryItem: PantryItem, onDismiss: () -> Unit, onFinish: (String, Int, LocalDate) -> Unit) {

    var itemName by remember { mutableStateOf(pantryItem.name) }
    var itemAmount by remember { mutableStateOf(pantryItem.amount) }
    var itemDueDate = pantryItem.dueDate
    var showItemNameErrorMessage by remember { mutableStateOf(false) }
    var showItemAmountErrorMessage by remember { mutableStateOf(false) }
    var showItemDueDateErrorMessage by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = itemName,
                onValueChange = {

                    if(it.isNotEmpty()){
                        showItemNameErrorMessage = false
                    }

                    itemName = it
                },
                label = { Text("New item name") },
                modifier = Modifier.fillMaxWidth(),
            )
            if (showItemNameErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Field should be not empty",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            OutlinedTextField(
                value = itemAmount.toString(),
                onValueChange = { amountValue ->

                    if(amountValue.isEmpty()){
                        return@OutlinedTextField
                    }

                    val newAmount = amountValue.toInt()

                    if(newAmount > 0){
                        showItemAmountErrorMessage = false
                    }

                    itemAmount = newAmount
                },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
            )
            if (showItemAmountErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Amount should be more than 0",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            DatePickerDocked(selectedDate = itemDueDate.toString()){ selectedDate ->

                if(selectedDate.isAfter(LocalDate.now())){
                    showItemDueDateErrorMessage = false
                }

                itemDueDate = selectedDate
            }

            if (showItemDueDateErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Due date should be more than today date",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp), horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        if(itemName.isEmpty()){
                            showItemNameErrorMessage = true
                        }

                        if(itemAmount <= 0){
                            showItemAmountErrorMessage = true
                        }

                        if(itemDueDate.isBefore(LocalDate.now())){
                            showItemDueDateErrorMessage = true
                        }

                        if(showItemDueDateErrorMessage || showItemNameErrorMessage || showItemAmountErrorMessage){
                            return@Button
                        }

                        onFinish(itemName, itemAmount, itemDueDate)

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Save", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
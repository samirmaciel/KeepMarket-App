package com.sm.keepmarket.presentation.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sm.keepmarket.presentation.theme.KeepMarketTheme
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

@Composable
fun AddNewPantryItemModal(onDismiss: () -> Unit, onFinish: (String, Int, LocalDate) -> Unit) {

    var itemName by remember { mutableStateOf("") }
    var itemAmount by remember { mutableStateOf(0) }
    var itemDueDate = LocalDate.now().minusDays(1)
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

            DatePickerDocked{ selectedDate ->

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
                    Text("Add", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {

    KeepMarketTheme {

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(selectedDate: String? = null, onSelectedDate: (LocalDate) -> Unit) {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(selectedDate ?: "") }
    val localFocus = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text("Due Date") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .onFocusEvent{
                    if(it.isFocused){
                        showDatePicker = true
                    }
                }
        )

        if (showDatePicker) {
            DatePickerModal({ selectedDateMillis ->
                selectedDate = selectedDateMillis?.let {
                    convertMillisToDate(it)
                } ?: ""

                if(selectedDateMillis != null){
                    onSelectedDate(
                        Instant.ofEpochMilli(selectedDateMillis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate())
                }

            }, {
                showDatePicker = false
                localFocus.clearFocus()
            })
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
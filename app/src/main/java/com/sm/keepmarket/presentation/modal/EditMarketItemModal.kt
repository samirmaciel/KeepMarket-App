package com.sm.keepmarket.presentation.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.Red
import java.math.BigDecimal

@Composable
fun EditMarketItemModal(marketItem: MarketItem, onDismiss: () -> Unit, onFinish: (String, Int, BigDecimal) -> Unit) {

    var name by remember { mutableStateOf(marketItem.name) }
    var amount by remember { mutableStateOf(marketItem.amount) }
    var price by remember { mutableStateOf(marketItem.price) }
    var showNameErrorMessage by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = name,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newName ->
                    name = newName
                },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
            )

            if (showNameErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Field should be not empty",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = Red
                )
            }

            OutlinedTextField(
                value = amount.toString(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { amountValue ->

                    if(amountValue.isEmpty()){
                        amount = 0
                        return@OutlinedTextField
                    }

                    val newAmount = amountValue.toInt()

                    amount = newAmount
                },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = price.toString(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { priceValue ->

                    if(priceValue.isEmpty()){
                        price = BigDecimal.ZERO
                        return@OutlinedTextField
                    }

                    val newPrice = BigDecimal(priceValue)

                    price = newPrice
                },
                label = { Text("Unit price") },
                modifier = Modifier.fillMaxWidth(),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp), horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        if (name.isEmpty()) {
                            showNameErrorMessage = true
                            return@Button
                        }

                        onFinish(name, amount, price)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
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
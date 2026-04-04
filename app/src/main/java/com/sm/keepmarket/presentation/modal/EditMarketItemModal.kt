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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.util.CurrencyUtil
import java.math.BigDecimal
import java.util.Locale

@Composable
fun EditMarketItemModal(marketItem: MarketItem, onDismiss: () -> Unit, onFinish: (String, String, Double, BigDecimal) -> Unit) {

    var productName by remember { mutableStateOf(marketItem.productName) }
    var name by remember { mutableStateOf(marketItem.name) }
    var amount by remember { mutableDoubleStateOf(marketItem.amount) }
    var price by remember { mutableStateOf(TextFieldValue(CurrencyUtil.bigDecimalToCurrency(marketItem.price, Locale("pt", "BR")))) }
    var showNameErrorMessage by remember { mutableStateOf(false) }
    var showProductNameErrorMessage by remember { mutableStateOf(false) }
    var showAmountErrorMessage by remember { mutableStateOf(false) }
    var showPriceErrorMessage by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = productName,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newProductName ->
                    productName = newProductName
                },
                label = { Text(stringResource(R.string.hint_product_name)) },
                modifier = Modifier.fillMaxWidth(),
            )

            if (showProductNameErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = stringResource(R.string.message_field_empty_error),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            OutlinedTextField(
                value = name,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newName ->
                    name = newName
                },
                label = { Text(stringResource(R.string.hint_item_name)) },
                modifier = Modifier.fillMaxWidth(),
            )

            if (showNameErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = stringResource(R.string.message_field_empty_error),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            OutlinedTextField(
                value = amount.toString(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { amountValue ->

                    if(amountValue.isEmpty()){
                        amount = 0.0
                        return@OutlinedTextField
                    }

                    val newAmount = amountValue.toDouble()

                    amount = newAmount
                },
                label = { Text(stringResource(R.string.hint_amount)) },
                modifier = Modifier.fillMaxWidth(),
            )

            if (showAmountErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = stringResource(R.string.message_field_empty_error),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            OutlinedTextField(
                value = price,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { priceValue ->

                    if(priceValue.text.isNotEmpty()){
                        val formatted = CurrencyUtil.formatterTextToCurrency(priceValue.text, Locale("pt", "BR"))

                        formatted?.let {
                            price = TextFieldValue(text = it, selection = TextRange(it.length))
                        }
                    }
                },
                label = { Text(stringResource(R.string.hint_unit_price)) },
                modifier = Modifier.fillMaxWidth(),
            )

            if (showPriceErrorMessage) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = stringResource(R.string.message_field_empty_error),
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

                        if (productName.isEmpty()) {
                            showProductNameErrorMessage = true
                            return@Button
                        }

                        if (amount <= 0.0) {
                            showAmountErrorMessage = true
                            return@Button
                        }

                        if (name.isEmpty()) {
                            showNameErrorMessage = true
                            return@Button
                        }

                        val formatedPrice = CurrencyUtil.parseCurrencyToBigDecimal(price.text, Locale("pt", "BR"))

                        if (formatedPrice <= BigDecimal.ZERO ) {
                            showPriceErrorMessage = true
                            return@Button
                        }

                        onFinish(productName, name, amount, CurrencyUtil.parseCurrencyToBigDecimal(price.text, Locale("pt", "BR")))
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(stringResource(R.string.label_save), style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
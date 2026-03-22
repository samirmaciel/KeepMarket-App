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
fun CheckMarketItemModal(marketItem: MarketItem, onDismiss: () -> Unit, onFinish: (String, Int, BigDecimal) -> Unit) {

    var productName by remember { mutableStateOf(marketItem.productName) }
    var amount by remember { mutableIntStateOf(marketItem.amount) }
    var price by remember { mutableStateOf(TextFieldValue(CurrencyUtil.bigDecimalToCurrency(marketItem.price, Locale("pt", "BR"))))}
    var showAmountErrorMessage by remember { mutableStateOf(false) }
    var showPriceErrorMessage by remember { mutableStateOf(false) }
    var showProductNameErrorMessage by remember { mutableStateOf(false) }

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
                    keyboardType = KeyboardType.Text
                ),
                onValueChange = { newProductName ->

                    if (productName.isEmpty()) {
                        showAmountErrorMessage = false
                    }

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

                    if (newAmount > 0) {
                        showAmountErrorMessage = false
                    }

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
                            showPriceErrorMessage = false
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

                        if (amount <= 0) {
                            showAmountErrorMessage = true
                        }

                        val value = CurrencyUtil.parseCurrencyToBigDecimal(price.text, Locale("pt", "BR"))

                        if ( value <= BigDecimal.ZERO) {
                            showPriceErrorMessage = true
                        }

                        if ( productName.isEmpty()) {
                            showProductNameErrorMessage = true
                        }

                        if(showAmountErrorMessage || showPriceErrorMessage || showProductNameErrorMessage){
                            return@Button
                        }

                        onFinish(productName, amount, value)

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(stringResource(R.string.label_check), style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

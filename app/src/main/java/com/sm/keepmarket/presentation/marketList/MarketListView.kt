package com.sm.keepmarket.presentation.marketList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.presentation.components.AlertMessage
import com.sm.keepmarket.presentation.components.MarketItemListView
import com.sm.keepmarket.presentation.modal.AddNewItemModal
import com.sm.keepmarket.util.CurrencyUtil
import com.sm.keepmarket.util.UiState
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun MarketListView(marketListID: String, paddingValues: PaddingValues) {

    val viewModel: MarketListViewModel = koinViewModel()
    var showAddNewItemModal by remember { mutableStateOf(false) }
    var showSaveAlertMessage by remember { mutableStateOf(false) }
    val uiState = viewModel.uiState.collectAsState()
    val navController = LocalNavHostController.current

    LaunchedEffect(Unit) {
        viewModel.getMarket(marketListID)
    }

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                when (uiState.value.state) {
                    UiState.LOADING -> {
                        CircularProgressIndicator()
                    }

                    UiState.LOADED -> {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = uiState.value.market?.name ?: "Not founded",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }

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
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    stringResource(R.string.label_add_item),
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 12.sp
                )
            }

            if (showAddNewItemModal) {
                AddNewItemModal(
                    onDismiss = { showAddNewItemModal = false },
                    onFinish = { itemName ->
                        viewModel.addNewItem(itemName)
                        showAddNewItemModal = false
                    })
            }

            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .weight(1f)
            )

//            Button(
//                onClick = {
//
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = MaterialTheme.colorScheme.tertiary
//                ),
//                shape = RoundedCornerShape(5.dp)
//            ) {
//                Text(
//                    stringResource(R.string.label_uncheck_all_items),
//                    style = MaterialTheme.typography.labelMedium,
//                    color = MaterialTheme.colorScheme.onTertiary,
//                    fontSize = 12.sp
//                )
//            }
        }

        when (uiState.value.state) {
            UiState.LOADING -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { CircularProgressIndicator() }
            }

            UiState.LOADED -> {
                LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).weight(1f)) {
                    items(uiState.value.market?.items ?: emptyList()) { item ->
                        MarketItemListView(
                            item,
                            onEdit = { editedItem ->
                                viewModel.editItem(editedItem)
                            },
                            onDeleted = { toDeleteItem ->
                                viewModel.deleteItem(toDeleteItem)
                            }
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier
                .size(1.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "${stringResource(R.string.label_total)} ${
                    CurrencyUtil.bigDecimalToCurrency(
                        viewModel.getTotalItemCheckedValue(),
                        Locale("pt", "BR")
                    )
                }",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .weight(1f)
            )
            Button(
                modifier = Modifier.padding(end = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    showSaveAlertMessage = true
                }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.label_save),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "${uiState.value.market?.items?.filter { it.isChecked }?.size}/${uiState.value.market?.items?.size}",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

        }

        if (showSaveAlertMessage) {
            AlertMessage(
                stringResource(R.string.message_save_confirmation),
                onConfirm = {
                    viewModel.finishItemState()
                    showSaveAlertMessage = false
                },
                onDismiss = { showSaveAlertMessage = false })
        }

    }
}
package com.sm.keepmarket.presentation.pantryList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.LocalNavHostController
import com.sm.keepmarket.R
import com.sm.keepmarket.components.PantryItemListView
import com.sm.keepmarket.presentation.modal.AddNewPantryItemModal
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.Red
import com.sm.keepmarket.util.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PantryListView(pantryListID: String, paddingValues: PaddingValues) {

    val viewModel: PantryViewModel = koinViewModel()
    var showAddNewItemModal by remember { mutableStateOf(false) }
    val uiState = viewModel.uiState.collectAsState()
    val navController = LocalNavHostController.current

    LaunchedEffect(Unit) {
        viewModel.getPantry(pantryListID)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(paddingValues)
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

        when(uiState.value.state){
            UiState.LOADING -> {
                CircularProgressIndicator()
            }
            UiState.LOADED -> {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = uiState.value.pantry?.name ?: "Not founded",
                    style = MaterialTheme.typography.titleLarge
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
                AddNewPantryItemModal(onDismiss = {
                    showAddNewItemModal = false
                }) { itemName, itemAmount, itemDueDate ->
                    showAddNewItemModal = false
                    viewModel.addNewItem(itemName, itemAmount, itemDueDate)
                }
            }

            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .weight(1f)
            )

            Button(
                onClick = {
                    viewModel.removeExpiredItems()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Remove expired items",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 12.sp
                )
            }
        }

        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
            items(uiState.value.pantry?.items ?: emptyList()) { item ->
                PantryItemListView(item,
                    onEdit = { editedItem ->
                        viewModel.editItem(editedItem)
                    },
                    onDeleted = { toDeleteItem ->
                        viewModel.deleteItem(toDeleteItem)
                    })
            }
        }
    }
}


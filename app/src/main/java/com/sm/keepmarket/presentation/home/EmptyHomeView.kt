package com.sm.keepmarket.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sm.keepmarket.presentation.modal.CreateNewListModal
import com.sm.keepmarket.presentation.theme.Blue

@Composable
fun EmptyHomeView(
    userName: String,
    onCreateMarketList: (String) -> Unit,
    onCreatePantryList: (String) -> Unit
) {

    var showCreateMarketList by remember { mutableStateOf(false) }
    var showCreatePantryList by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello $userName!", style = MaterialTheme.typography.titleLarge)

        Column(modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    showCreateMarketList = true
                }, modifier = Modifier.height(200.dp).fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Create Market List")
            }
            Button(
                onClick = {
                    showCreatePantryList = true
                }, modifier = Modifier.height(200.dp).fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Create Pantry List")
            }
        }
    }

    if (showCreateMarketList){
        CreateNewListModal(
            hint = "Market list name",
            onDismiss = { showCreateMarketList = false },
            onFinish = { marketListName ->
                onCreateMarketList(marketListName)
                showCreateMarketList = false
            })
    }

    if (showCreatePantryList){
        CreateNewListModal(
            hint = "Pantry list name",
            onDismiss = { showCreatePantryList = false },
            onFinish = { pantryListName ->
                onCreatePantryList(pantryListName)
                showCreatePantryList = false
            })
    }

}
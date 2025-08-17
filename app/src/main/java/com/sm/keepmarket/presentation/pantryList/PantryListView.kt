package com.sm.keepmarket.presentation.pantryList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.components.MarketListItem
import com.sm.keepmarket.domain.PantryItem
import com.sm.keepmarket.presentation.theme.Background
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.Red
import com.sm.keepmarket.util.Mock
import java.math.BigDecimal

@Composable
fun PantryListView(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(paddingValues)
    ) {

        IconButton(modifier = Modifier.padding(16.dp), onClick = {

        }) {
            Icon(
                painter = painterResource(R.drawable.arrowlefticon),
                tint = Color.Unspecified,
                contentDescription = "Arrow back view"
            )
        }

        Text(
            modifier = Modifier.padding(16.dp),
            text = "Pantry List Name",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Add new Item", style = MaterialTheme.typography.labelSmall)
            }

            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .weight(1f)
            )

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Remove expired items", style = MaterialTheme.typography.labelSmall)
            }
        }

        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
            items(Mock.getPantryItemList()) { item ->
                PantryListItem(item)
            }
        }
    }
}

@Composable
fun PantryListItem(pantryItem: PantryItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .height(60.dp)
            .background(color = pantryItem.getDueColor(), shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        var expanded by remember { mutableStateOf(false) }

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

        Spacer(modifier = Modifier
            .size(10.dp)
            .weight(1f))


        Text(
            modifier = Modifier.padding(end = 5.dp),
            text = "● ${pantryItem.amount}",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 15.sp,
            color = Color.White
        )


        Box() {
            IconButton(onClick = {
                expanded = true
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
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { }
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
                        .clickable { }
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

}
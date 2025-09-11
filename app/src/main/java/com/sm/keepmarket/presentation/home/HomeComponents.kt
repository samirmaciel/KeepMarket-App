package com.sm.keepmarket.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.components.HighlightItemView
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.presentation.modal.CreateNewListModal
import com.sm.keepmarket.presentation.theme.Blue
import com.sm.keepmarket.presentation.theme.ButtonDefault


@Composable
fun HighlightList(items: List<Highlight>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(items) { highlight ->
            HighlightItemView(highlight)
            Spacer(modifier = Modifier.size(5.dp))
        }
    }
}

@Composable
fun EmptyHighlightList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(300.dp),
            painter = painterResource(R.drawable.flagicon), tint = ButtonDefault,
            contentDescription = "Empty highlight list"
        )
    }
}


@Composable
fun CreateList(onCreateMarket: (String) -> Unit, onCreatePantry: (String) -> Unit) {

    var showCreateMarketList by remember { mutableStateOf(false) }
    var showCreatePantryList by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .weight(1f),
            onClick = { showCreateMarketList = true },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text(
                "Create Market List",
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Button(
            modifier = Modifier
                .weight(1f), onClick = {
                showCreatePantryList = true
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text(
                "Create Pantry List",
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
            )
        }
    }

    if (showCreateMarketList) {
        CreateNewListModal(
            hint = "Market list name",
            onDismiss = { showCreateMarketList = false },
            onFinish = { marketListName ->
                onCreateMarket(marketListName)
                showCreateMarketList = false
            })
    }

    if (showCreatePantryList) {
        CreateNewListModal(
            hint = "Pantry list name",
            onDismiss = { showCreatePantryList = false },
            onFinish = { pantryListName ->
                onCreatePantry(pantryListName)
                showCreatePantryList = false
            })
    }

}
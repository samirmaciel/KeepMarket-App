package com.sm.keepmarket.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.sm.keepmarket.components.FeaturedCardButton
import com.sm.keepmarket.components.HighlightItemView
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.modal.CreateNewListModal
import com.sm.keepmarket.util.FeaturedType
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(paddingValues: PaddingValues) {

    val nav = LocalNavHostController.current
    val viewModel : HomeViewModel = koinViewModel()
    val featuredCardList by viewModel.featuredCardList.collectAsState()
    val highlightList by viewModel.highlightList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.settingsicon),
                    contentDescription = "Settings button"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalAlignment = Alignment.Start
        ) {
            Text("Hello User!", style = MaterialTheme.typography.titleLarge)
            Text("Have a nice day.", style = MaterialTheme.typography.labelSmall)
        }

        var showCreateMarketList by remember { mutableStateOf(false) }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.padding(start = 10.dp),
                onClick = { showCreateMarketList = true },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    "Prices",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp
                )
            }
            Button(
                modifier = Modifier.padding(start = 10.dp), onClick = {},
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    "Lost items",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp
                )
            }
            Button(
                modifier = Modifier.padding(start = 10.dp), onClick = {},
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    "Most used",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp
                )
            }
        }

        if (showCreateMarketList) {
            CreateNewListModal(
                hint = "Market list name",
                onDismiss = { showCreateMarketList = false },
                onFinish = { marketListName ->
                    showCreateMarketList = false
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            LazyRow {
                items(featuredCardList) { featuredCard ->
                    FeaturedCardButton(featuredCard = featuredCard) { featuredCard ->
                        when(featuredCard.featuredType){
                            FeaturedType.MARKET -> nav.navigate(Dest.MarketListView)
                            FeaturedType.PANTRY -> nav.navigate(Dest.PantryListView)
                        }
                    }
                }
            }

        }

        Text(
            modifier = Modifier.padding(16.dp),
            text = "Highlights",
            style = MaterialTheme.typography.labelMedium
        )

        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(highlightList) { hightlight ->
                HighlightItemView(hightlight)
                Spacer(modifier = Modifier.size(5.dp))
            }
        }
    }
}